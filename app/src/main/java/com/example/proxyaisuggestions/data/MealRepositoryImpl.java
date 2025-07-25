package com.example.proxyaisuggestions.data;

import androidx.lifecycle.LiveData;

import com.example.proxyaisuggestions.data.db.MealDao;
import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.List;

import jakarta.inject.Inject;

public class MealRepositoryImpl implements MealRepository {

    private final MealDao mealDao;

    @Inject
    public MealRepositoryImpl(MealDao mealDao) {
        this.mealDao = mealDao;
    }
    @Override
    public LiveData<List<Meal>> getMealsByIds(List<Integer> mealIds) {
        return mealDao.getMealsByIds(mealIds);
    }
    @Override
    public LiveData<List<Category>> getCategories() {
        return mealDao.getAllCategories();
    }

    @Override
    public LiveData<List<Meal>> getMealsByCategoryId(int categoryId) {
        return mealDao.getMealsByCategoryId(categoryId);
    }

}