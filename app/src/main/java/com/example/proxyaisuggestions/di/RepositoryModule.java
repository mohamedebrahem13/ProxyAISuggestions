package com.example.proxyaisuggestions.di;

import com.example.proxyaisuggestions.data.MealRepository;
import com.example.proxyaisuggestions.data.MealRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    @Singleton
    public abstract MealRepository bindMealRepository(MealRepositoryImpl impl);
}