package com.mpersson.myrecipeorganizer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe_table")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "ingredients")
    private String[] mIngredients;
    @ColumnInfo(name = "directions")
    private String[] mDirections;

    public Recipe(@NonNull String name, String description, String[] ingredients, String[] directions) {
        this.mName = name;
        this.mDescription = description;
        this.mIngredients = ingredients;
        this.mDirections = directions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String[] getDirections() {
        return mDirections;
    }

    public String[] getIngredients() {
        return mIngredients;
    }
}
