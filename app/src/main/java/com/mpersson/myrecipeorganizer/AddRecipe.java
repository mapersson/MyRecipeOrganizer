package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddRecipe extends AppCompatActivity {

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

        addIngredients = findViewById(R.id.text_recipe_description);

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
}