package com.example.recipegenius.ui.recipepage;

import static android.speech.tts.TextToSpeech.Engine.ACTION_CHECK_TTS_DATA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.w3c.dom.Text;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.EngineInfo;

import com.example.recipegenius.databinding.FragmentRecipepageBinding;

public class RecipePageFragment extends Fragment implements TextToSpeech.OnInitListener {

    private FragmentRecipepageBinding binding;
    TextView recipeName;
    ImageView recipePageImage;
    TextToSpeech tts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipePageModel recipePageViewModel =
                new ViewModelProvider(this).get(RecipePageModel.class);

        binding = FragmentRecipepageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences currentRecipe = getActivity().getSharedPreferences("currentRecipe", Context.MODE_PRIVATE);
        String name = currentRecipe.getString("r_name", "No recipe name found");
        // currentRecipe.getString("r_ingredients", "No ingredients found");
        // currentRecipe.getString("r_instructions", "No instructions found");
        // currentRecipe.getString("r_tags", "No tags found");
        String imageUri = currentRecipe.getString("r_imageURL", "No image found");

        recipeName = (TextView) root.findViewById(R.id.recipePageName);
        recipeName.setText(name);

        recipePageImage = (ImageView) root.findViewById(R.id.recipePageImage);
        Picasso.get().load(imageUri).fit().centerCrop().into(recipePageImage);

        System.out.println(name);
        System.out.println(imageUri);

        Set<String> set_ingr = currentRecipe.getStringSet("r_ingredients", null);
        Set<String> set_inst = currentRecipe.getStringSet("r_instructions", null);
        Set<String> set_tags = currentRecipe.getStringSet("r_tags", null);

        String ingr = "";
        String inst = "";
        String tags = "";

        if (set_ingr != null) {
            for (int i = 0; i < set_ingr.size(); i++) {
                ingr += i+1 + ". " + set_ingr.toArray()[i] + "\n";
            }
        }

        if (set_inst != null) {
            for (int i = 0; i < set_inst.size(); i++) {
                inst += i+1 + ". " + set_inst.toArray()[i] + "\n";
            }
        }

        if (set_tags != null) {
            for (String s : set_tags) {
                tags += s + ", ";
            }
        }

        TextView ingrDisp = (TextView) root.findViewById(R.id.recipePageIngredients);
        ingrDisp.setText(ingr);

        TextView instDisp = (TextView) root.findViewById(R.id.recipePageInstructions);
        instDisp.setText(inst);

        TextView tagsDisp = (TextView) root.findViewById(R.id.recipePageTags);
        tagsDisp.setText(tags);

        // TTS
        final int[] currentInstruction = {1};
        final String[] currentInstructionString = {set_inst.toArray()[currentInstruction[0] - 1].toString()};
        // get size of instructions from Set<String> set_inst
        int totalInstructions = set_inst.size();
        // print to console log
        System.out.println("Total instructions: " + totalInstructions);

        Button previousButton = (Button) root.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // decrement the instruction number if it is greater than 2
                if (currentInstruction[0] > 1) {
                    currentInstruction[0] = currentInstruction[0] - 1;
                    // get the current instruction from the set
                    currentInstructionString[0] = set_inst.toArray()[currentInstruction[0] - 1].toString();
                    // print to console log
                    System.out.println("Current instruction: " + currentInstructionString[0]);
                } else {
                    System.out.println("Minimum number of instructions reached: " + 1);
                }
            }
        });

        Button nextButton = (Button) root.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // increment the instruction number if it is less than the total number of instructions
                if (currentInstruction[0] < totalInstructions) {
                    currentInstruction[0] = currentInstruction[0] + 1;
                    // get the current instruction from the set
                    currentInstructionString[0] = set_inst.toArray()[currentInstruction[0] -1].toString();
                    // print to console log
                    System.out.println("Current instruction: " + currentInstructionString[0]);
                } else {
                    System.out.println("Maximum number of instructions reached: " + totalInstructions);
                }
            }
        });
        // on currentInstruction change, speak the current instruction
        // get installed tts engines
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        tts = new TextToSpeech(getContext(), this);
        for (TextToSpeech.EngineInfo engines : tts.getEngines()) {
            System.out.println("Engine Info " + engines.toString());
        }



        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                System.out.println("TextToSpeech onInit: " + i + " " + TextToSpeech.SUCCESS);

                if(i == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.ENGLISH);
                    System.out.println("TextToSpeech setLanguage: " + Locale.ENGLISH);
                } else {
                    System.out.println("TextToSpeech initialization failed");
                }
            }
        });
        Button currentButton = (Button) root.findViewById(R.id.currentButton);
        currentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // speak the current instruction
                // speak(CharSequence, int, bundle, String)
                tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        // Button recipebutton = (Button) root.findViewById(R.id.myRecipesButton);
        // recipebutton.setOnClickListener(new View.OnClickListener() {
        //     public void onClick(View v) {
        //         Navigation.findNavController(v).navigate(R.id.navigation_my_recipes);
        //     }
        // });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


    @Override
    public void onInit(int status) {
        System.out.println("TextToSpeech onInit: " + status + " " + TextToSpeech.SUCCESS);

        if(status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.CANADA);
            System.out.println("TextToSpeech setLanguage: " + Locale.CANADA);
        } else {
            System.out.println("TextToSpeech initialization failed");
        }

    }
}