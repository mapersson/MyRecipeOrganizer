package com.mpersson.myrecipeorganizer.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mpersson.myrecipeorganizer.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * from recipe_table ORDER BY name ASC")
    LiveData<List<Recipe>> getAllRecipes();

    @Insert
    void insert(Recipe recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAll();
}
