package com.example.proxyaisuggestions.ui;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proxyaisuggestions.data.models.Meal;
import com.example.proxyaisuggestions.databinding.ItemMealBinding;

import java.util.ArrayList;
import java.util.List;

import coil.ImageLoader;
import coil.request.ImageRequest;


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<Meal> meals = new ArrayList<>();
    private List<Meal> originalMeals = new ArrayList<>();

    public interface OnMealCountChangeListener {
        void onCountChanged(Meal meal, int newCount);
    }

    private final OnMealCountChangeListener countChangeListener;

    public MealAdapter(OnMealCountChangeListener listener) {
        this.countChangeListener = listener;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        this.originalMeals = new ArrayList<>(meals); // Save original meals
        notifyDataSetChanged();
    }


    public List<Meal> getMeals() {
        return meals;
    }
    public List<Meal> getOriginalMeals() {
        return originalMeals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMealBinding binding = ItemMealBinding.inflate(inflater, parent, false);
        return new MealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        private final ItemMealBinding binding;

        MealViewHolder(ItemMealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("DefaultLocale")
        void bind(Meal meal) {
            binding.tvMealName.setText(meal.getName());
            binding.tvMealPrice.setText(String.format("%.2f SAR", meal.getPrice()));
            binding.tvCount.setText(String.valueOf(meal.getCount()));
            Log.d("MealAdapter", "Loading image: " + meal.getImageUrl());

            // Load the image from URL using Coil
            ImageLoader imageLoader = new ImageLoader.Builder(binding.ivMealImage.getContext()).build();

            ImageRequest request = new ImageRequest.Builder(binding.ivMealImage.getContext())
                    .data(meal.getImageUrl())
                    .target(binding.ivMealImage)
                    .build();

            imageLoader.enqueue(request);
            binding.btnPlus.setOnClickListener(v -> {
                int count = meal.getCount() + 1;
                meal.setCount(count);
                binding.tvCount.setText(String.valueOf(count));
                countChangeListener.onCountChanged(meal, count);
            });

            binding.btnMinus.setOnClickListener(v -> {
                int count = meal.getCount();
                if (count > 0) {
                    count--;
                    meal.setCount(count);
                    binding.tvCount.setText(String.valueOf(count));
                    countChangeListener.onCountChanged(meal, count);
                }
            });
        }
    }
}