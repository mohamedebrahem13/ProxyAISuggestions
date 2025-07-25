package com.example.proxyaisuggestions.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proxyaisuggestions.R;
import com.example.proxyaisuggestions.data.models.Category;
import com.example.proxyaisuggestions.data.models.Meal;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MealFragment extends Fragment implements
        CategoryAdapter.OnCategoryClickListener,
        MealAdapter.OnMealCountChangeListener {

    private MealViewModel viewModel;
    private CategoryAdapter categoryAdapter;
    private MealAdapter mealAdapter;
    private ImageView ivSearchIcon;
    private TextView tvCartTotal;

    private double cartTotal = 0.0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews(view);
        setupRecyclerViews();
        setupViewModel();
        observeViewModel();
    }


    private void setupViews(View view) {
        ivSearchIcon = view.findViewById(R.id.ivSearchIcon);
        tvCartTotal = view.findViewById(R.id.tvCartTotal);

        ivSearchIcon.setOnClickListener(v -> showSearchDialog());

        ConstraintLayout bottomCartView = view.findViewById(R.id.bottomCartView);
        bottomCartView.setOnClickListener(v -> {
            Bundle bundle = getBundle();

            CartFragment cartFragment = new CartFragment();
            cartFragment.setArguments(bundle);

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, cartFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @NonNull
    private Bundle getBundle() {
        ArrayList<Integer> selectedMealIds = new ArrayList<>();
        double totalPrice = 0.0;

        for (Meal meal : mealAdapter.getMeals()) {
            if (meal.getCount() > 0) {
                selectedMealIds.add(meal.getId());
                totalPrice += meal.getPrice() * meal.getCount();
            }
        }

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("selected_meal_ids", selectedMealIds);
        bundle.putDouble("total_price", totalPrice);
        return bundle;
    }

    private void setupRecyclerViews() {
        RecyclerView rvCategories = requireView().findViewById(R.id.rvCategories);
        RecyclerView rvMeals = requireView().findViewById(R.id.rvMeals);

        categoryAdapter = new CategoryAdapter(this);
        rvCategories.setLayoutManager(
                new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        rvCategories.setAdapter(categoryAdapter);

        mealAdapter = new MealAdapter(this);
        int spanCount = 2;
        rvMeals.setLayoutManager(new GridLayoutManager(requireContext(), spanCount));
        rvMeals.setAdapter(mealAdapter);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MealViewModel.class);
    }

    private void observeViewModel() {
        viewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), categories -> {
            categoryAdapter.setCategories(categories);
            if (!categories.isEmpty()) {
                categoryAdapter.setSelectedPosition(0);
                viewModel.selectCategory(categories.get(0).getId());
            }
        });

        viewModel.getMealsLiveData().observe(getViewLifecycleOwner(), meals -> {
            mealAdapter.setMeals(meals);
            updateCartTotal(meals);
        });
    }
    private void showSearchDialog() {
        EditText input = new EditText(requireContext());
        input.setHint("بحث");

        new AlertDialog.Builder(requireContext())
                .setTitle("بحث")
                .setView(input)
                .setPositiveButton("بحث", (dialog, which) -> {
                    String query = input.getText().toString();
                    filterMeals(query);
                })
                .setNegativeButton("إلغاء", null)
                .show();
    }

    private void filterMeals(String query) {
        List<Meal> allMeals = mealAdapter.getOriginalMeals(); // Recommended: store unfiltered list
        if (allMeals == null || allMeals.isEmpty()) return;

        if (query == null || query.trim().isEmpty()) {
            mealAdapter.setMeals(allMeals);
            return;
        }

        String lowerQuery = query.toLowerCase();
        List<Meal> filtered = new ArrayList<>();
        for (Meal meal : allMeals) {
            if (meal.getName().toLowerCase().contains(lowerQuery)) {
                filtered.add(meal);
            }
        }
        mealAdapter.setMeals(filtered);
    }

    @Override
    public void onCategoryClick(Category category, int position) {
        categoryAdapter.setSelectedPosition(position);
        viewModel.selectCategory(category.getId());

        // Show a Toast with the category name
        Toast.makeText(requireContext(), "Selected: " + category.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountChanged(Meal meal, int newCount) {
        updateCartTotal(mealAdapter.getMeals());
    }

    @SuppressLint("DefaultLocale")
    private void updateCartTotal(List<Meal> meals) {
        cartTotal = 0.0;
        if (meals != null) {
            for (Meal meal : meals) {
                cartTotal += meal.getPrice() * meal.getCount();
            }
        }
        tvCartTotal.setText(String.format("%.2f SAR", cartTotal));
    }
}
