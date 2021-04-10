package com.mpersson.myrecipeorganizer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mDescription);
        dest.writeStringArray(this.mIngredients);
        dest.writeStringArray(this.mDirections);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in.readString(), in.readString(), in.createStringArray(), in.createStringArray());
        }
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

}
