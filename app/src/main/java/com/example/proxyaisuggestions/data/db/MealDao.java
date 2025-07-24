package com.example.proxyaisuggestions.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertCategories(List<Category> categories);

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeals(List<Meal> meals);

    @Update
    void updateMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM meals WHERE id = :id LIMIT 1")
    Meal getMealById(int id);

    @Query("SELECT * FROM meals WHERE categoryId = :categoryId")
    LiveData<List<Meal>> getMealsByCategoryId(int categoryId);
}