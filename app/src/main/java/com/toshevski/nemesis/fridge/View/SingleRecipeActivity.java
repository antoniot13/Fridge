package com.toshevski.nemesis.fridge.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;
import java.util.Random;

public class SingleRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int s = getIntent().getIntExtra("int_value",0);
        Data d = new Data(this);
        //ArrayList<Recipe> recipes= StaticData.getRecipes();
        ArrayList<Recipe> recipes = d.getAllReceiptsWithProducts();
        Recipe r = recipes.get(s);
        Log.d("INFOOO:SRA", r.Products.size() + " SIZE");
        Log.d("INFO:SRA", r.Products.get(0).Name);
        ArrayList<String> products=new ArrayList<>();
        for (Product p : r.Products) {
            if (p.Quantity < 1) {
                Random rand = new Random();
                products.add(rand.nextInt(12) + "    " + p.Name);
            } else products.add(p.Quantity + "    " + p.Name);
        }
        Log.d("INFO:SRA", products.size() + " SIZE");
        Log.d("INFO:SRA", products.get(0));
        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        ListView codeLearnLessons = (ListView)findViewById(R.id.singleRecipeList);
        codeLearnLessons.setAdapter(codeLearnArrayAdapter);
        codeLearnArrayAdapter.notifyDataSetChanged();

        TextView tmp=(TextView) findViewById(R.id.textView6);
        tmp.setText(recipes.get(s).Description);
    }

}
