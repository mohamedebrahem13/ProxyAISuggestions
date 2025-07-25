package com.example.proxyaisuggestions.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.proxyaisuggestions.data.MealRepository;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CartViewModel extends ViewModel {

    private final MealRepository repository;
    private final MutableLiveData<List<Integer>> selectedMealIds = new MutableLiveData<>();
    private final LiveData<List<Meal>> mealsLiveData;

    @Inject
    public CartViewModel(MealRepository repository) {
        this.repository = repository;
        mealsLiveData = Transformations.switchMap(selectedMealIds, repository::getMealsByIds);
    }

    public void setSelectedMealIds(List<Integer> ids) {
        selectedMealIds.setValue(ids);
    }

    public LiveData<List<Meal>> getMealsLiveData() {
        return mealsLiveData;
    }
}