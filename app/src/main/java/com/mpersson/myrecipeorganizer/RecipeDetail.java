package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.Arrays;
import java.util.LinkedList;

public class RecipeDetail extends AppCompatActivity {
    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mThirdActivity;
    private RecyclerView mIngRecyclerView;
    private RecyclerView mDescRecyclerView;
    private StringListAdapter mIngAdapter;
    private StringListAdapter mDirectionAdapter;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra("RECIPE");

        mIngRecyclerView = findViewById(R.id.recyclerviewIngredients);
        String[] ingredients = mRecipe.getIngredients();
        mIngAdapter = new StringListAdapter(this,new LinkedList<String>(Arrays.asList(ingredients)));
        mIngRecyclerView.setAdapter(mIngAdapter);
        mIngRecyclerView.setAdapter(mIngAdapter);
        mIngRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mDescRecyclerView = findViewById(R.id.recyclerviewDirections);
        String[] directions = mRecipe.getDirections();
        mDirectionAdapter = new StringListAdapter(this, new LinkedList<String>(Arrays.asList(directions)));
        mDescRecyclerView.setAdapter(mDirectionAdapter);
        mDescRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}





