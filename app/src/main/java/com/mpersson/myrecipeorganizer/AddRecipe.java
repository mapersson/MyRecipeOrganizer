package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.mpersson.myrecipeorganizer.model.Recipe;

public class AddRecipe extends AppCompatActivity {
    public static final String NEW_RECIPE = "com.mpersson.myrecipeorganizer.NEW_RECIPE";
    TextView mRecipeName;
    TextView mRecipeDescription;
    Recipe newRecipe;

    private TextView addIngredients, addDescription;
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

    public void btnUploadRecipe(View view) {

        /*
       Intent replyIntent = new Intent();

        if (TextUtils.isEmpty(addIngredients.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String ingredients = addIngredients.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, ingredients);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
        
         */
    }

    public void btnAddRecipe(View view) {
        String name = mRecipeName.getText().toString();
        String description = mRecipeDescription.getText().toString();
        newRecipe = new Recipe(name, description, null, null);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(NEW_RECIPE, newRecipe);
        setResult(RESULT_OK, replyIntent);
        finish();

    }


}