package com.mpersson.myrecipeorganizer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "description")
    private String mDescription;

    public Recipe(@NonNull String name, String description) {
        this.mName = name;
        this.mDescription = description;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

}
