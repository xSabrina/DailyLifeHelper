package com.example.rober.dailylifehelper.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.rober.dailylifehelper.RecipeList.Recipe;
import com.example.rober.dailylifehelper.ToDoList.ToDoItem;

@Database(entities = {ToDoItem.class, Recipe.class}, version = 2, exportSchema = true)
public abstract class MyDatabase extends RoomDatabase{
    public abstract ToDoDao toDoDao();
    public abstract RecipeDao recipeDao();
}
