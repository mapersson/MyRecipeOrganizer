package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.Arrays;
import java.util.LinkedList;

public class ThirdActivity extends AppCompatActivity {
    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mThirdActivity;
    private RecyclerView mIngRecyclerView;
    private RecyclerView mDescRecyclerView;
    private StringListAdapter mIngAdapter;
    private StringListAdapter mDescriptionAdapter;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra("RECIPE");

        mIngRecyclerView = findViewById(R.id.recyclerviewIngredients);
        String[] ingredients = mRecipe.getIngredients();
        mIngAdapter = new StringListAdapter(this,new LinkedList<String>(Arrays.asList(ingredients)));
        mIngRecyclerView.setAdapter(mIngAdapter);
        mIngRecyclerView.setAdapter(mIngAdapter);
        mIngRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}





