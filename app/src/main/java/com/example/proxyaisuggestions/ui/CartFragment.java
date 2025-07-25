package com.example.proxyaisuggestions.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.proxyaisuggestions.data.models.Meal;
import com.example.proxyaisuggestions.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;
    private MealAdapter cartMealAdapter; // Reusing MealAdapter

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);

        cartMealAdapter = new MealAdapter((meal, newCount) -> {
            // Optional: Update cart total when quantity changes
            updateTotal(cartMealAdapter.getMeals());
        });

        binding.rvCartMeals.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvCartMeals.setAdapter(cartMealAdapter);
        binding.btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        Bundle args = getArguments();
        if (args != null) {
            ArrayList<Integer> selectedMealIds = args.getIntegerArrayList("selected_meal_ids");
            double totalPrice = args.getDouble("total_price", 0.0);

            Log.d("CartFragment", "Received selectedMealIds: " + selectedMealIds);
            Log.d("CartFragment", "Received totalPrice: " + totalPrice);

            binding.tvCartTotal.setText(String.format("%.2f SAR", totalPrice));

            cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
            cartViewModel.setSelectedMealIds(selectedMealIds);

            cartViewModel.getMealsLiveData().observe(getViewLifecycleOwner(), meals -> {
                Log.d("CartFragment", "Meals received from ViewModel: " + meals.size());
                cartMealAdapter.setMeals(meals);

                // Only update total if no total passed from arguments (fallback)
                if (totalPrice == 0.0) {
                    updateTotal(meals);
                } else {
                    Log.d("CartFragment", "Using total from arguments: " + totalPrice);
                }
            });
        } else {
            Log.d("CartFragment", "No arguments received in Bundle.");
        }

        return binding.getRoot();
    }

    @SuppressLint("DefaultLocale")
    private void updateTotal(List<Meal> meals) {
        double total = 0.0;
        for (Meal meal : meals) {
            total += meal.getCount() * meal.getPrice();
        }
        binding.tvCartTotal.setText(String.format("%.2f SAR", total));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}