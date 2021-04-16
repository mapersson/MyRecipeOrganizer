package com.mpersson.myrecipeorganizer.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpersson.myrecipeorganizer.R;
import com.mpersson.myrecipeorganizer.model.Recipe;


@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RecipeRoomDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
    private static RecipeRoomDatabase INSTANCE;
    private static Context mContext;

    public static RecipeRoomDatabase getDatabase(final Context context) {
        mContext = context;
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
            new PopulateDbAsync(INSTANCE, mContext).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final RecipeDao mDao;
        private final Context mContext;


        PopulateDbAsync(RecipeRoomDatabase db, Context context) {
            mDao = db.recipeDao();
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            String[] ingredientsOne = mContext.getResources().getStringArray(R.array.RecipeOneIng);
            String[] directionsOne = mContext.getResources().getStringArray(R.array.RecipeOneDir);
            String titleOne = mContext.getResources().getString(R.string.RecipeOneTitle);
            String descriptionOne = mContext.getResources().getString(R.string.RecipeOneDescription);
            Recipe recipeOne = new Recipe(titleOne, descriptionOne, ingredientsOne, directionsOne);

            String[] ingredientsTwo = mContext.getResources().getStringArray(R.array.RecipeTwoIng);
            String[] directionsTwo = mContext.getResources().getStringArray(R.array.RecipeTwoDir);
            String titleTwo = mContext.getResources().getString(R.string.RecipeTwoTitle);
            String descriptionTwo = mContext.getResources().getString(R.string.RecipeTwoDescription);
            Recipe recipeTwo = new Recipe(titleTwo, descriptionTwo, ingredientsTwo, directionsTwo);

            String[] ingredientsThree = mContext.getResources().getStringArray(R.array.RecipeThreeIng);
            String[] directionsThree = mContext.getResources().getStringArray(R.array.RecipeThreeDir);
            String titleThree = mContext.getResources().getString(R.string.RecipeThreeTitle);
            String descriptionThree = mContext.getResources().getString(R.string.RecipeThreeDescription);
            Recipe recipeThree = new Recipe(titleThree, descriptionThree, ingredientsThree, directionsThree);

            String[] ingredientsFour = mContext.getResources().getStringArray(R.array.RecipeFourIng);
            String[] directionsFour = mContext.getResources().getStringArray(R.array.RecipeFourDir);
            String titleFour = mContext.getResources().getString(R.string.RecipeFourTitle);
            String descriptionFour = mContext.getResources().getString(R.string.RecipeFourDescription);
            Recipe recipeFour = new Recipe(titleFour, descriptionFour, ingredientsFour, directionsFour);

            mDao.insert(recipeOne);
            mDao.insert(recipeTwo);
            mDao.insert(recipeThree);
            mDao.insert(recipeFour);

            return null;
        }
    }
}
