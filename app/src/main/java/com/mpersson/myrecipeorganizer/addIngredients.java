package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.ArrayList;
import java.util.LinkedList;

public class addIngredients extends AppCompatActivity {
    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mIngredient;
    private RecyclerView mRecyclerView;
    private Button mAddIngredient;
    private StringListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);
        mIngredient = findViewById(R.id.txtIngredient);
        mAddIngredient = findViewById(R.id.btnAddIngredient);
        mAdapter = new StringListAdapter(this,mStringList);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void btnReturnIngredients(View view) {
        String[] mIngredientList = mStringList.toArray( new String[mStringList.size()]);
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Ingredients", mIngredientList);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void btnSaveIngredient(View view) {
        mStringList.addLast(mIngredient.getText().toString());
        mRecyclerView.getAdapter().notifyItemInserted(mStringList.size());
    }



}