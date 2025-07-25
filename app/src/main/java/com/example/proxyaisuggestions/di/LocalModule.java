package com.example.proxyaisuggestions.di;


import android.content.Context;

import androidx.room.Room;

import com.example.proxyaisuggestions.data.MealRepository;
import com.example.proxyaisuggestions.data.MealRepositoryImpl;
import com.example.proxyaisuggestions.data.db.MealDao;
import com.example.proxyaisuggestions.data.db.MealDatabase;
import com.example.proxyaisuggestions.data.db.MealDatabaseCallback;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalModule {

    @Provides
    @Singleton
    public MealDatabase provideMealDatabase(@ApplicationContext Context context, MealDatabaseCallback callback) {
        return Room.databaseBuilder(context, MealDatabase.class, "meal_database")
                .fallbackToDestructiveMigration(false)
                .addCallback(callback)
                .build();
    }

    @Provides
    @Singleton
    public MealDao provideMealDao(MealDatabase mealDatabase) {
        return mealDatabase.mealDao();
    }
}