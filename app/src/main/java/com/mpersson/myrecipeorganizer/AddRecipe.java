package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mpersson.myrecipeorganizer.model.Recipe;

public class AddRecipe extends AppCompatActivity {
    public static final String NEW_RECIPE = "com.mpersson.myrecipeorganizer.NEW_RECIPE";
    TextView mRecipeName;
    TextView mRecipeDescription;
    Recipe newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add You Recipe Here!");

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
        newRecipe = new Recipe(name, description, null, null);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(NEW_RECIPE, newRecipe);
        setResult(RESULT_OK, replyIntent);
        finish();

    }


}