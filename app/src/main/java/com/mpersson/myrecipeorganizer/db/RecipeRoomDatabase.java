package com.mpersson.myrecipeorganizer.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpersson.myrecipeorganizer.model.Recipe;


@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
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


        String[] ingredients = new String[]{"1 pound (450g) green beans, trimmed", "3 tablespoons butter, divided or (ghee for paleo diet)", "4 garlic cloves, minced"};
        String[] directions = new String[]
                {
                        "1. To make the lemon garlic butter chicken thighs recipe with green beans: In a small bowl, combine onion powder, paprika, salt, and pepper. Season chicken thighs generously with the spice mixture. Set aside while you prepare green beans.",
                        "2. Arrange green beans in a microwave-safe dish with 1/2 cup (125ml) water. Cook in the microwave for 8-10 minutes, until almost done but still crisp.",
                        "3. Melt 2 tablespoons butter in a large skillet over medium-low heat. Lay the seasoned chicken thighs in one layer in the skillet. Cook for 5-6 minutes then flip and cook another 5-6 minutes, until cooked through and a cooking thermometer displays 165°F (75°C). If chicken browns too quickly, lower the heat. Adjust timing depending on the thickness. Transfer chicken to a plate and set aside.",
                        "4. In the same skillet, lower the heat and melt the remaining tablespoon of butter. Add chopped parsley, garlic, hot sauce, red crushed chili pepper flakes, and pre-cooked green beans and cook for 4 to 5 minutes, stirring regularly, until cooked to your liking. Add lemon juice and chicken stock and reduce the sauce for a couple of minutes, until slightly thickened.",
                        "5. Add cooked chicken thighs back to the pan and reheat quickly. Adjust seasoning with pepper and serve the lemon garlic butter chicken thighs immediately, garnished with more crushed chili pepper, fresh parsley, and a slice of lemon if you like. Enjoy!"
                };

        Recipe recipeOne = new Recipe("Pancakes", "Buttermilk pancakes with icing sugar", ingredients, directions);
        Recipe recipeTwo = new Recipe("Bread", "Sourdough bread with other stuff.", ingredients, directions);
        Recipe recipeThree = new Recipe("Cake", "Sourdough cake with other stuff.", ingredients, directions);

        Recipe[] recipes = {recipeOne, recipeTwo, recipeThree};


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
