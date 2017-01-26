package com.toshevski.nemesis.fridge.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {

    ProductAdapter pa;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        products = new ArrayList<>();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //tryToAddReceipt();

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.hide();*/
        // tuka implementacija za button
        findViewById(R.id.action_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(AddRecipeActivity.this,MainActivity.class);
                startActivity(simple);
            }
        });

        findViewById(R.id.action_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(AddRecipeActivity.this,AddProductActivity.class);
                startActivity(simple);
            }
        });

        ImageButton btn=(ImageButton) findViewById(R.id.imageButton);
        pa = new ProductAdapter();
        final ListView productsInAddRecipe = (ListView)findViewById(R.id.listView);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Product p =new Product("",0,false);
                EditText tmp=(EditText) findViewById(R.id.editText2);
                if(!tmp.getText().toString().matches("")) {
                    pa = new ProductAdapter();
                    productsInAddRecipe.setAdapter(pa);
                    p.Name=tmp.getText().toString();
                    products.add(p);
                    pa.notifyDataSetChanged();
                    productsInAddRecipe.setAlpha(1);
                }
                tmp.setText("");

            }

        });
    }

    private void tryToAddReceipt() {
        Recipe r = new Recipe("Shtarechi", new ArrayList<Product>(), "nekoj golem opis");
        Data d = new Data(this);
        ArrayList<Product> p = d.getAllProducts();
        r.Products.add(p.get(0));
        r.Products.add(p.get(1));
        r.Products.add(p.get(2));
        r.Products.add(p.get(3));
        d.insertIntoReceipts(r);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            ListView productsInAddRecipe = (ListView)findViewById(R.id.listView);
            pa=new ProductAdapter();
            productsInAddRecipe.setAdapter(pa);
            pa.products.clear();

            EditText tmp=(EditText) findViewById(R.id.editText2);
            EditText tmp1=(EditText) findViewById(R.id.editText1);
            EditText tmp2=(EditText) findViewById(R.id.editText3);
            tmp1.setText("");
            tmp2.setText("");
            tmp.setText("");


        }*/
        if (id == R.id.action_add) {
            EditText name = (EditText) findViewById(R.id.editText1);
            EditText desc = (EditText) findViewById(R.id.editText3);

            Recipe r = new Recipe(name.getText().toString(), products, desc.getText().toString());
            Data d = new Data(this);
            d.insertIntoReceipts(r);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProductAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Product getItem(int arg0) {
            return products.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            LayoutInflater inflater = (LayoutInflater) AddRecipeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.list_view_add_recipe, arg2, false);

            TextView name = (TextView)arg1.findViewById(R.id.textView1);


            Product product = products.get(arg0);

            name.setText(product.Name);

            if(product.IsAvailable) {
                name.setTypeface(null, Typeface.BOLD);
            }
            return arg1;
        }
    }

}


