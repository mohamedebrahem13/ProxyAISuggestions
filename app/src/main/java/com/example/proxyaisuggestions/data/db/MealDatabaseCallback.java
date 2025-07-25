package com.example.proxyaisuggestions.data.db;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class MealDatabaseCallback extends RoomDatabase.Callback {

    private final Provider<MealDatabase> mealDatabaseProvider;
    private final Executor ioExecutor = Executors.newSingleThreadExecutor();

    @Inject
    public MealDatabaseCallback(Provider<MealDatabase> mealDatabaseProvider) {
        this.mealDatabaseProvider = mealDatabaseProvider;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        ioExecutor.execute(() -> {
            MealDatabase mealDatabase = mealDatabaseProvider.get();
            if (mealDatabase == null) {
                return;
            }
            com.example.proxyaisuggestions.data.db.MealDao mealDao = mealDatabase.mealDao();

            // Insert categories
            List<Category> categories = new ArrayList<>();
            categories.add(new Category("أفضل العروض"));
            categories.add(new Category("أجبان"));
            categories.add(new Category("أجبان قابلة للدهن"));
            categories.add(new Category("مستورد"));

            // Insert categories and get their generated IDs
            List<Long> categoryIds = mealDao.insertCategories(categories);

            // Prepare meals with category IDs from inserted categories
            List<Meal> meals = new ArrayList<>();
            meals.add(new Meal("Double Whopper", 29.57, "Beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Steakhouse XL", 35.65, "Big beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Chicken Steakhouse", 37.39, "Chicken burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(1).intValue()));
            meals.add(new Meal("Quattro Cheese Grill", 29.57, "Cheesy chicken", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(2).intValue()));
            meals.add(new Meal("Double Whopper", 29.57, "Beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Steakhouse XL", 35.65, "Big beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Chicken Steakhouse", 37.39, "Chicken burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(1).intValue()));
            meals.add(new Meal("Quattro Cheese Grill", 29.57, "Cheesy chicken", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(2).intValue()));
            meals.add(new Meal("Double Whopper", 29.57, "Beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Steakhouse XL", 35.65, "Big beef burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(0).intValue()));
            meals.add(new Meal("Chicken Steakhouse", 37.39, "Chicken burger", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(1).intValue()));
            meals.add(new Meal("Quattro Cheese Grill", 29.57, "Cheesy chicken", "https://th.bing.com/th/id/R.94b70e56383b63fcab90b82d627c4c58?rik=iOfMZGbvGX%2bRxw&pid=ImgRaw&r=0", categoryIds.get(2).intValue()));

            mealDao.insertMeals(meals);
        });
    }
}