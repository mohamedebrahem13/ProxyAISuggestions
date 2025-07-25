package com.example.proxyaisuggestions.data;

import androidx.lifecycle.LiveData;

import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.List;

public interface MealRepository {
    LiveData<List<Meal>> getMealsByIds(List<Integer> mealIds);

    LiveData<List<Category>> getCategories();
    LiveData<List<Meal>> getMealsByCategoryId(int categoryId);
}