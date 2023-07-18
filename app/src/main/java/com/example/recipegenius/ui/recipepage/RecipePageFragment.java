package com.example.recipegenius.ui.recipepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.Set;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.w3c.dom.Text;

import com.example.recipegenius.R;
import com.squareup.picasso.Picasso;


import com.example.recipegenius.databinding.FragmentRecipepageBinding;

public class RecipePageFragment extends Fragment {

    private FragmentRecipepageBinding binding;
    TextView recipeName;
    ImageView recipePageImage;

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


}