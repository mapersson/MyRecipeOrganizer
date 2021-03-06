package com.mpersson.myrecipeorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
//import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpersson.myrecipeorganizer.model.Recipe;
import com.mpersson.myrecipeorganizer.viewmodel.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private RecipeViewModel mRecipeViewModel;
    private NewHeadlineProvider mHeadlineProvider;
    private String mHeadline = "";
    private TextView headlineView;

    int[] pic = {R.drawable.recipe1, R.drawable.recipe2, R.drawable.recipe3, R.drawable.recipe4, R.drawable.recipe5, R.drawable.recipe6, R.drawable.recipe7, R.drawable.recipe8, R.drawable.recipe9, R.drawable.recipe10, R.drawable.recipe11, R.drawable.recipe12, R.drawable.recipe13, R.drawable.recipe14};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome to M&I Kitchen!");
        actionBar.setIcon(R.drawable.ic_welcome);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headlineView = findViewById(R.id.txtHeadLine);
        mHeadlineProvider = new NewHeadlineProvider(headlineView);
        mHeadlineProvider.execute("https://gnews.io/api/v4/top-headlines?topic=breaking-news&country=ca&token=dab97892125a9e40154596b8a7fad98d&lang=en&max=1");

        gridView = (GridView) findViewById(R.id.gridViewId);
        CustomAdapter adapter = new CustomAdapter(this, pic);
        gridView.setAdapter(adapter);

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.setRecipes(recipes);
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, RecipeDetail.class);
                intent.putExtra("RECIPE", (Recipe) adapter.getItem(i));
                startActivity(intent);

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
            case R.id.map:
                Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                startActivityForResult(intent1, 4);
                Toast.makeText(this, "Map clicked", Toast.LENGTH_SHORT).show();

                break;

            case R.id.add:
                Intent intent = new Intent(MainActivity.this, AddRecipe.class);
                startActivityForResult(intent, 2);

                Toast.makeText(this, "Add clicked", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            Recipe newRecipe = data.getParcelableExtra(AddRecipe.NEW_RECIPE);
            mRecipeViewModel.insert(newRecipe);

        }


    }


}