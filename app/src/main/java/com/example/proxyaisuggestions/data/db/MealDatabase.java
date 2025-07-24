package com.example.proxyaisuggestions.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

@Database(entities = {Meal.class, Category.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    public abstract MealDao mealDao();
}