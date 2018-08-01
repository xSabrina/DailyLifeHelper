package com.example.rober.dailylifehelper.RecipeList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.rober.dailylifehelper.R;


public class RecipeList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setupUI();
        execAsync();
    }

    private void setupUI(){
        setContentView(R.layout.list_recipe);
    }
    //works
    private void execAsync(){
        new RecipeAsyncTask().execute("test");
    }

}
