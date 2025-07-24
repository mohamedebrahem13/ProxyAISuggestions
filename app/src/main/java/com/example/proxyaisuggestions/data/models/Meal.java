package com.example.proxyaisuggestions.data.models;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "categoryId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Meal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double price;

    private String description;

    @Nullable
    private String imageUrl;

    private int categoryId;

    public Meal(String name, double price, String description, @Nullable String imageUrl, int categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter for imageUrl
    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getter and setter for categoryId
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}