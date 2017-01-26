package com.toshevski.nemesis.fridge.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Model.Recipe;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class MyRecipesActivity extends AppCompatActivity   {
    RecipeAdapter ra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        ra = new RecipeAdapter(getApplicationContext());
        ListView marketsInListView = (ListView) findViewById(R.id.listRecipes);
        marketsInListView.setAdapter(ra);
        ra.notifyDataSetChanged();
        marketsInListView.setAlpha(1);
        marketsInListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MyRecipesActivity.this, SingleRecipeActivity.class).putExtra("int_value", position);
                startActivity(myintent);
            }
        });
    }

    /**
     * Adapter za receptite
     */
    public class RecipeAdapter extends BaseAdapter {

        ArrayList<Recipe> recipes;// = StaticData.getRecipes();

        public RecipeAdapter(Context ctx) {
            Data d = new Data(ctx);
            recipes = d.getAllReceiptsWithProducts();
            Log.i("INFOOO:", recipes.size() + "");
            for (Recipe r: recipes) {
                Log.i("INFOOO:", r.Name);
                Log.i("INFOOO:", r.Products.size() + "");
            }
        }

        @Override
        public int getCount() {
            return recipes.size();
        }

        @Override
        public Recipe getItem(int arg0) {
            return recipes.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            LayoutInflater inflater = (LayoutInflater) MyRecipesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.recipes_list_view, arg2, false);

            TextView name = (TextView)arg1.findViewById(R.id.textView2);


            Recipe recipe = recipes.get(arg0);

            name.setText(recipe.Name);

            return arg1;
        }
    }


}
