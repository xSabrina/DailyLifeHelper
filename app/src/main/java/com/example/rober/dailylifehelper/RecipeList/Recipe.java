package com.example.rober.dailylifehelper.RecipeList;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Recipe {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int recipeId;
    private String recipeName;
    //finish recipes


    public int getRecipeId() {
        return recipeId;
    }
    @NonNull
    public String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName(@NonNull String recipeName){
        this.recipeName = recipeName;
    }

    public void setRecipeId(@NonNull int recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe(){
    }
}
