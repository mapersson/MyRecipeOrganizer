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

    private TextView addIngredients, addDescription, addDirections;
    private Button addRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Your Recipe Here!");

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        mRecipeDescription = findViewById(R.id.text_recipe_description);
        mRecipeName = findViewById(R.id.text_recipe_name);
    }

    public void btnSelectImage(View view) {
    }


    public void btnAddRecipe(View view) {
        String name = mRecipeName.getText().toString();
        String description = mRecipeDescription.getText().toString();
        newRecipe = new Recipe(name, description, mIngredients, mDirections);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(NEW_RECIPE, newRecipe);
        setResult(RESULT_OK, replyIntent);
        finish();

    }


    public void btnAddIngredient(View view) {
        Intent intent = new Intent(AddRecipe.this, addIngredients.class);
        startActivityForResult(intent, ADD_INGREDIENTS);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_INGREDIENTS && resultCode == RESULT_OK) {
            mIngredients = data.getStringArrayExtra("Ingredients");
            return;

        }
        if (requestCode == ADD_DIRECTIONS && resultCode == RESULT_OK) {
            mDirections = data.getStringArrayExtra("Directions");
            return;
        }
    }

    public void btnAddDirection(View view) {

        Intent intent = new Intent(AddRecipe.this, AddDirections.class);
        startActivityForResult(intent, ADD_DIRECTIONS);


    }


}