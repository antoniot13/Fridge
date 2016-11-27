package com.toshevski.nemesis.fridge.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
     ProductAdapter pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.hide();
        ImageButton btn=(ImageButton) findViewById(R.id.imageButton);
        pa = new ProductAdapter();
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                ListView productsInAddRecipe = (ListView)findViewById(R.id.listView);
                productsInAddRecipe.setAdapter(pa);
                Product p =new Product("",0,false);
                EditText tmp=(EditText) findViewById(R.id.editText2);
                if(!tmp.getText().toString().matches("")) {
                    p.Name=tmp.getText().toString();


                    pa.products.add(p);
                    pa.notifyDataSetChanged();
                    productsInAddRecipe.setAlpha(1);


                }
                tmp.setText("");

            }

        });
    }

    public class ProductAdapter extends BaseAdapter {

        ArrayList<Product> products = new ArrayList<Product>();

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


