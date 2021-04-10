package com.mpersson.myrecipeorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mpersson.myrecipeorganizer.model.Recipe;
import com.mpersson.myrecipeorganizer.viewmodel.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private RecipeViewModel mRecipeViewModel;

    int [] pic = {R.drawable.recipe1, R.drawable.recipe2, R.drawable.recipe3, R.drawable.recipe4, R.drawable.recipe5, R.drawable.recipe6, R.drawable.recipe7, R.drawable.recipe8, R.drawable.recipe9, R.drawable.recipe10, R.drawable.recipe11, R.drawable.recipe12, R.drawable.recipe13, R.drawable.recipe14};
    String[] foodNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome to M&I Kitchen!");
        actionBar.setIcon(R.drawable.ic_welcome);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodNames = getResources().getStringArray(R.array.food);

        gridView=(GridView)findViewById(R.id.gridViewId);
        CustomAdapter adapter = new CustomAdapter(this, foodNames, pic);
        gridView.setAdapter(adapter);


        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.setRecipes(recipes);
            }
        });


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

                if(i==2) {
                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    startActivity(intent);
                }




            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.refresh:
                Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.add:
                startActivity(new Intent(MainActivity.this, AddRecipe.class));
                Toast.makeText(this, "Add clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}