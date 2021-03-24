package com.mpersson.myrecipeorganizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    int [] pic = {R.drawable.recipe1, R.drawable.recipe2, R.drawable.recipe3, R.drawable.recipe4, R.drawable.recipe5, R.drawable.recipe6, R.drawable.recipe7, R.drawable.recipe8, R.drawable.recipe9, R.drawable.recipe10, R.drawable.recipe11, R.drawable.recipe12, R.drawable.recipe13, R.drawable.recipe14};
    String[] foodNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodNames = getResources().getStringArray(R.array.food);

        gridView=(GridView)findViewById(R.id.gridViewId);

        CustomAdapter adapter = new CustomAdapter(this, foodNames, pic);
        gridView.setAdapter(adapter);


        //onClick function Creacted

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
                    startActivity(intent);
                }

                    if(i==1) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }


            }
        });
    }
}