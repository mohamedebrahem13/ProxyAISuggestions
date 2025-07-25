package com.example.proxyaisuggestions.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.proxyaisuggestions.data.MealRepository;
import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

@HiltViewModel
public class MealViewModel extends ViewModel {

    private final MealRepository repository;
    private final MutableLiveData<Integer> selectedCategoryId = new MutableLiveData<>();
    private final LiveData<List<Category>> categoriesLiveData;
    private final LiveData<List<Meal>> mealsLiveData;

    private final Observer<List<Category>> categoryObserver = categories -> {
        if (categories != null && !categories.isEmpty() && selectedCategoryId.getValue() == null) {
            selectedCategoryId.postValue(categories.get(0).getId());
        }
    };

    @Inject
    public MealViewModel(MealRepository repository) {
        this.repository = repository;

        categoriesLiveData = repository.getCategories();

        mealsLiveData = Transformations.switchMap(selectedCategoryId, categoryId -> {
            if (categoryId == null) return new MutableLiveData<>();
            return repository.getMealsByCategoryId(categoryId);
        });

        categoriesLiveData.observeForever(categoryObserver);
    }

    public LiveData<List<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }

    public LiveData<List<Meal>> getMealsLiveData() {
        return mealsLiveData;
    }

    public LiveData<Integer> getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void selectCategory(int categoryId) {
        selectedCategoryId.setValue(categoryId);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        categoriesLiveData.removeObserver(categoryObserver);
    }
}