package com.mpersson.myrecipeorganizer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.ArrayList;

public class AddRecipe extends AppCompatActivity {

    public static final String NEW_RECIPE = "com.mpersson.myrecipeorganizer.NEW_RECIPE";
    public static final int ADD_INGREDIENTS = 2;
    public static final int ADD_DIRECTIONS = 3;
    TextView mRecipeName;
    TextView mRecipeDescription;
    Recipe newRecipe;
    String[] mIngredients;
    String[] mDirections;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.abAddRecipeTitleBar));

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        mRecipeDescription = findViewById(R.id.text_recipe_description);
        mRecipeName = findViewById(R.id.text_recipe_name);
    }

    public void btnAddRecipe(View view) {
        String name = mRecipeName.getText().toString();
        String description = mRecipeDescription.getText().toString();


        if(mIngredients == null)
        {

            Toast.makeText(this, R.string.add_ingredients, Toast.LENGTH_SHORT).show();
            return;
        }

        if(mDirections == null)
        {
            Toast.makeText(this, R.string.add_directions, Toast.LENGTH_SHORT).show();
            return;
        }



        if(mRecipeName.getText().length() == 0) {

            Toast.makeText(this, R.string.add_recipe_name, Toast.LENGTH_SHORT).show();
            return;

        }

        if(mRecipeDescription.getText().length() == 0) {
            Toast.makeText(this, R.string.add_description, Toast.LENGTH_SHORT).show();
            return;
        }


        newRecipe = new Recipe(name, description, mIngredients, mDirections);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(NEW_RECIPE, newRecipe);
        setResult(RESULT_OK, replyIntent);
        finish();

    }


    public void btnAddIngredient(View view) {
        Intent intent = new Intent(AddRecipe.this, AddDirections.class);
        intent.putExtra("txtBoxHint", R.string.add_ingredients);
        startActivityForResult(intent, ADD_INGREDIENTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_INGREDIENTS && resultCode == RESULT_OK) {
            mIngredients = data.getStringArrayExtra("Inputs");
            return;
        }
        if (requestCode == ADD_DIRECTIONS && resultCode == RESULT_OK) {
            mDirections = data.getStringArrayExtra("Inputs");
            return;
        }

    }

    public void btnAddDirections(View view) {

        Intent intent = new Intent(AddRecipe.this, AddDirections.class);
        intent.putExtra("txtBoxHint", R.string.add_directions);
        startActivityForResult(intent, ADD_DIRECTIONS);

    }

}