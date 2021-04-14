package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class AddDirections extends AppCompatActivity {

    private final LinkedList<String> mStringList = new LinkedList<>();
    private EditText mDirections;
    private RecyclerView mRecyclerView;
    private Button mAddDirections;
    private StringListAdapter mAdapter;
    private String txtHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions);

        Intent intent = getIntent();
        txtHint = intent.getStringExtra("txtBoxHint");

        mDirections = findViewById(R.id.txtDirections);
        mDirections.setHint(txtHint);
        mAddDirections = findViewById(R.id.btnAddDirections);
        mAdapter = new StringListAdapter(this, mStringList);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void btnSaveDirections(View view) {

        mStringList.addLast(mDirections.getText().toString());
        mRecyclerView.getAdapter().notifyItemInserted(mStringList.size());
        mDirections.setText("");

    }

    public void btnReturnDirections(View view) {

        if(mStringList.size() == 0) {
            Toast.makeText(this, txtHint, Toast.LENGTH_SHORT).show();
            return;
        }

        String[] mDirectionList = mStringList.toArray(new String[mStringList.size()]);

        Intent replyIntent = new Intent();
        replyIntent.putExtra("Inputs", mDirectionList);
        setResult(RESULT_OK, replyIntent);
        finish();

    }
}