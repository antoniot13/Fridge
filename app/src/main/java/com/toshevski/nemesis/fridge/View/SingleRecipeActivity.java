package com.toshevski.nemesis.fridge.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class SingleRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        // tuka implementacija za button
        findViewById(R.id.action_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(SingleRecipeActivity.this,AddProductActivity.class);
                startActivity(simple);
            }
        });

        findViewById(R.id.action_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleRecipeActivity.this, "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
            }
        });

        int s= getIntent().getIntExtra("int_value",0);
        ArrayList<Recipe> recipes= StaticData.getRecipes();
        recipes.get(s);
        ArrayList<String> products=new ArrayList<String>();
        for(Product p : recipes.get(s).Products)
        {
            products.add(p.Quantity+"    "+p.Name.toString());
        }
        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        ListView codeLearnLessons = (ListView)findViewById(R.id.singleRecipeList);
        codeLearnLessons.setAdapter(codeLearnArrayAdapter);

        TextView tmp=(TextView) findViewById(R.id.textView6);
        tmp.setText(recipes.get(s).Description);
    }

}
