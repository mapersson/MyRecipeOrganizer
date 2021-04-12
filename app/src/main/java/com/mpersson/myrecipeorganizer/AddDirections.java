package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

public class AddDirections extends AppCompatActivity {

    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mDirections;
    private RecyclerView mRecyclerView;
    private Button mAddDirections;
    private StringListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions);
        mDirections = findViewById(R.id.txtDirections);
        mAddDirections = findViewById(R.id.btnAddDirections);
        mAdapter = new StringListAdapter(this, mStringList);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void btnSaveDirections(View view) {

        mStringList.addLast(mDirections.getText().toString());
        mRecyclerView.getAdapter().notifyItemInserted(mStringList.size());


    }

    public void btnReturnDirections(View view) {

        String[] mDirectionList = mStringList.toArray(new String[mStringList.size()]);
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Directions", mDirectionList);
        setResult(RESULT_OK, replyIntent);
        finish();

    }
}