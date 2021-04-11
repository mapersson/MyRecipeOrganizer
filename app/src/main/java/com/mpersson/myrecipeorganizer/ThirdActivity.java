package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class ThirdActivity extends AppCompatActivity {
    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mThirdActivity;
    private RecyclerView mRecyclerView;
    private StringListAdapter mAdapter;
    private StringListAdapter mDescriptionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        mAdapter = new StringListAdapter(this,mStringList);
        mRecyclerView = findViewById(R.id.recyclerviewIngredients);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setAdapter(mDescriptionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}





