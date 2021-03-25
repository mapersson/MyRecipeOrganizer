package com.mpersson.myrecipeorganizer;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpersson.myrecipeorganizer.db.RecipeDao;
import com.mpersson.myrecipeorganizer.db.RecipeRoomDatabase;
import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.List;

public class RecipeRepository {
    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }


}
