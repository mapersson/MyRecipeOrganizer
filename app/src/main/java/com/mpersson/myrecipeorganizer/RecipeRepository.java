package com.mpersson.myrecipeorganizer;

import android.app.Application;
import android.os.AsyncTask;

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


    private static class insertAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao mAsyncTaskDao;
        insertAsyncTask(RecipeDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Recipe... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void insert(Recipe recipe) {
        new insertAsyncTask(mRecipeDao).execute(recipe);
    }



}
