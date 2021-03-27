package com.mpersson.myrecipeorganizer.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpersson.myrecipeorganizer.model.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeRoomDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeRoomDatabase INSTANCE;

    public static RecipeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecipeRoomDatabase.class, "recipe_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final RecipeDao mDao;

        Recipe recipeOne   = new Recipe("Pancakes","Buttermilk pancakes with icing sugar");
        Recipe recipeTwo   = new Recipe("Bread","Sourdough bread with other stuff.");
        Recipe recipeThree   = new Recipe("Cake","Sourdough cake with other stuff.");
        Recipe recipeFour = new Recipe("Fish", "Burger with potato");

        Recipe[] recipes = {recipeOne, recipeTwo, recipeThree, recipeFour};


        PopulateDbAsync(RecipeRoomDatabase db) {
            mDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            for (int i = 0; i < recipes.length; i++) {
                mDao.insert(recipes[i]);
            }
            return null;
        }
    }
}
