package com.mpersson.myrecipeorganizer.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpersson.myrecipeorganizer.RecipeRepository;
import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository mRepository;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mAllRecipes = mRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }
    public void insert(Recipe recipe) {mRepository.insert(recipe);}

}
