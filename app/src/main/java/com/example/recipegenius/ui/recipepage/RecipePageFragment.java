package com.example.recipegenius.ui.recipepage;

import static android.speech.tts.TextToSpeech.Engine.ACTION_CHECK_TTS_DATA;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.w3c.dom.Text;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.EngineInfo;
import android.widget.Toast;

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
                ingr += set_ingr.toArray()[i] + "\n";
            }
        }

        if (set_tags != null) {
            for (String s : set_tags) {
                tags += s + ", ";
            }
        }
        tags = tags.substring(0, tags.length() - 2);

        TextView ingrDisp = (TextView) root.findViewById(R.id.recipePageIngredients);
        ingrDisp.setText(ingr);

        TextView instDisp = (TextView) root.findViewById(R.id.recipePageInstructions);

        TextView tagsDisp = (TextView) root.findViewById(R.id.recipePageTags);
        tagsDisp.setText(tags);

        // ----------------- Text to Speech -----------------
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean handsfreeSet = sharedPreferences.getBoolean("HandsFree", false);
        System.out.println("HandsFree: " + handsfreeSet);

        // hide button if handsfree mode is off
        if(handsfreeSet) {
            Button currentButton = (Button) root.findViewById(R.id.currentButton);
            currentButton.setVisibility(View.VISIBLE);
            ImageButton speechButton = (ImageButton) root.findViewById(R.id.speechButton);
            speechButton.setVisibility(View.VISIBLE);
        } else {
            Button currentButton = (Button) root.findViewById(R.id.currentButton);
            currentButton.setVisibility(View.GONE);
            ImageButton speechButton = (ImageButton) root.findViewById(R.id.speechButton);
            speechButton.setVisibility(View.GONE);
        }

        final int[] currentInstruction = {1};
        final String[] currentInstructionString = {"Step " + currentInstruction[0] + ". " + set_inst.toArray()[currentInstruction[0] - 1].toString()};
        // get size of instructions from Set<String> set_inst
        int totalInstructions = set_inst.size();
        // print to console log
        System.out.println("Total instructions: " + totalInstructions);
      
        // on currentInstruction change, speak the current instruction
        // get installed tts engines
        // Intent intent = new Intent();
        // intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        // tts = new TextToSpeech(getContext(), this);
        // for (TextToSpeech.EngineInfo engines : tts.getEngines()) {
        //     System.out.println("Engine Info " + engines.toString());
        // }
        update_instruction(instDisp, set_inst, currentInstruction[0]);

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

        Button previousButton = (Button) root.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                previous_instruction(instDisp, set_inst, currentInstruction, currentInstructionString, totalInstructions, handsfreeSet);
            }
        });

        Button currentButton = (Button) root.findViewById(R.id.currentButton);
        currentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(handsfreeSet) {
                    tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

        Button nextButton = (Button) root.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                next_instruction(instDisp, set_inst, currentInstruction, currentInstructionString, totalInstructions, handsfreeSet);
            }
        });

        ImageButton speechButton = (ImageButton) root.findViewById(R.id.speechButton);
        speechButton.setImageResource(R.drawable.microphone_off);
        final boolean[] speech_detect = {false};

        ActivityResultLauncher<Intent> recordingActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        System.out.println("Speech to text: " + res.get(0));

                        process_speech(res.get(0), set_inst, instDisp, currentInstruction, currentInstructionString, totalInstructions, handsfreeSet);
                        
                    } else {
                        System.out.println("Speech to text failed");
                    }
                    speechButton.setImageResource(R.drawable.microphone_off);
                    speech_detect[0] = false;
                }
            }
        );

        speechButton.setOnClickListener(new View.OnClickListener() {
            // change checked state of button on click

            public void onClick(View v) {
                // on click flip between speech detect on and off and change between mic and mic slash icons
                String[] BIASING_STRINGS = {"Next", "Back", "Current", "Previous", "Forward", "This"};
                if (speech_detect[0] == false) {
                    speechButton.setImageResource(R.drawable.microphone);
                    speech_detect[0] = true;

                    Intent startRecordingIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    startRecordingIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startRecordingIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                    startRecordingIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start speaking");
                    startRecordingIntent.putExtra(RecognizerIntent.EXTRA_BIASING_STRINGS, BIASING_STRINGS);
                    try {
                        recordingActivityLauncher.launch(startRecordingIntent);
                        System.out.println("Speech to text res: " + startRecordingIntent);
                    }catch(Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        Toast.makeText(getActivity().getApplicationContext(), " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    
                } else {
                    speechButton.setImageResource(R.drawable.microphone_off);
                    speech_detect[0] = false;
                }
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

    public void update_instruction(TextView instDisp, Set<String> set_inst, int current_instruction) {
        String inst = "<p>";
        if (set_inst != null) {
            // for currentInstruction, bold the text
            for (int i = 1; i <= set_inst.size(); i++) {
                if (i == current_instruction) {
                    // bold the text
                    inst += "<b>" + i + ". " + set_inst.toArray()[i-1] + "</b><br>";
                } else {
                    inst += i + ". " + set_inst.toArray()[i-1] + "<br>";
                }
            }
        }
        inst += "<p>";
        instDisp.setText(Html.fromHtml(inst));
    }

    public void next_instruction(TextView instDisp, Set<String> set_inst, int[] currentInstruction, String[] currentInstructionString, int totalInstructions, boolean handsfreeSet) {
        // increment the instruction number if it is less than the total number of instructions
        if (currentInstruction[0] < totalInstructions) {
            currentInstruction[0] = currentInstruction[0] + 1;
            // get the current instruction from the set
            currentInstructionString[0] = "Step " + currentInstruction[0] + ". " + set_inst.toArray()[currentInstruction[0] - 1].toString();
            update_instruction(instDisp, set_inst, currentInstruction[0]);
            
            if(handsfreeSet) {
                tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
            }

            // print to console log
            System.out.println("Current instruction: " + currentInstructionString[0]);
        } else {
            System.out.println("Maximum number of instructions reached: " + totalInstructions);
        }
    }

    public void previous_instruction(TextView instDisp, Set<String> set_inst, int[] currentInstruction, String[] currentInstructionString, int totalInstructions, boolean handsfreeSet) {
        // decrement the instruction number if it is greater than 2
        if (currentInstruction[0] > 1) {
            currentInstruction[0] = currentInstruction[0] - 1;
            // get the current instruction from the set
            currentInstructionString[0] = "Step " + currentInstruction[0] + ". " + set_inst.toArray()[currentInstruction[0] - 1].toString();
            update_instruction(instDisp, set_inst, currentInstruction[0]);
            
            if(handsfreeSet) {
                tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
            }
            
            // print to console log
            System.out.println("Current instruction: " + currentInstructionString[0]);
        } else {
            System.out.println("Minimum number of instructions reached: " + 1);
        }
    }

    public void process_speech(String res, Set<String> set_inst, TextView instDisp, int[] currentInstruction, String[] currentInstructionString, int totalInstructions, boolean handsfreeSet) {
        // if speech to text is successful, check if result contains command "Next", "Back", "Current", "Previous", "Forward", "This"
        // Hashtable for words to numerical values
        Hashtable<String, Integer> words = new Hashtable<String, Integer>();
        words.put("first", 1);
        words.put("one", 1);
        words.put("second", 2);
        words.put("two", 2);
        words.put("third", 3);
        words.put("three", 3);
        words.put("four", 4);
        words.put("fifth", 5);
        words.put("five", 5);
        words.put("six", 6);
        words.put("seven", 7);
        words.put("eight", 8);
        words.put("nine", 9);
        words.put("ten", 10);

        // replace words to numerical values
        for(String key : words.keySet()) {
            if(res.contains(key)) {
                res = res.replace(key, words.get(key).toString());
            }
        }
        System.out.println("Speech to text: " + res);

        res = res.toLowerCase();

        // check if res contains value
        StringBuilder value = new StringBuilder();
        boolean found = false;
        
        if(res.contains("next") || res.contains("forward")) {
            System.out.println("Next instruction speech detected");
            next_instruction(instDisp, set_inst, currentInstruction, currentInstructionString, totalInstructions, handsfreeSet);
        } else if(res.contains("back") || res.contains("previous")) {
            System.out.println("Previous instruction speech detected");
            previous_instruction(instDisp, set_inst, currentInstruction, currentInstructionString, totalInstructions, handsfreeSet);
        } else if(res.contains("current") || res.contains("this") || res.contains("repeat") || res.contains("again") || res.contains("on")) {
            System.out.println("Current instruction speech detected");
            if(handsfreeSet) {
                tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } else if(res.contains("last")) {
            System.out.println("Last instruction speech detected");
            currentInstruction[0] = totalInstructions;
            // get the current instruction from the set and add Step #. to the front
            currentInstructionString[0] = "Step " + currentInstruction[0] + ". " + set_inst.toArray()[currentInstruction[0] - 1].toString();
            update_instruction(instDisp, set_inst, currentInstruction[0]);
            if(handsfreeSet) {
                tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } else if(res.matches(".*\\d.*")) {
            for(char c : res.toCharArray()){
                if(Character.isDigit(c)){
                    value.append(c);
                    found = true;
                } else if(found){
                    // If we already found a digit before and this char is not a digit, stop looping
                    break;                
                }
            }
            System.out.println("Value: " + value);
            if(value.length() > 0) {
                int val = Integer.parseInt(value.toString());
                if(val > 0 && val <= totalInstructions) {
                    System.out.println("Value detected: " + val);
                    currentInstruction[0] = val;
                    currentInstructionString[0] = "Step " + currentInstruction[0] + ". " + set_inst.toArray()[currentInstruction[0] - 1].toString();
                    update_instruction(instDisp, set_inst, currentInstruction[0]);
                    if(handsfreeSet) {
                        tts.speak(currentInstructionString[0], TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                } else {
                    System.out.println("Value out of range: " + val);
                }
            }
        } else {
            tts.speak("Sorry, I didn't get that. Could you repeat?", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        


        

        
    }

    
}