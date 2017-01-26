package com.toshevski.nemesis.fridge.View;

import com.amulyakhare.textdrawable.TextDrawable;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Database.FillDB;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ProductAdapter pa;
    ListView productsInListView;
    CircularProgressBar cpb;
    TextView limit;
    TextView budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Data d = new Data(getApplicationContext());
        FillDB fdb = new FillDB(getApplicationContext());
        if (d.getAllProducts().size() < 1)
            fdb.FillProducts();
        if (d.getAllMarkets().size() < 1)
            fdb.FillMarkets();
        if (d.getAllReceiptsWithProducts().size() < 1)
            fdb.FillReceipts();


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Implementation of adapter
        pa = new ProductAdapter(getApplicationContext());
        productsInListView = (ListView)findViewById(R.id.listProducts);
        productsInListView.setAdapter(pa);
        pa.notifyDataSetChanged();
        productsInListView.setAlpha(1);
        productsInListView.addHeaderView(makeHeader());
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent simple = new Intent(MainActivity.this,AddProductActivity.class);
                startActivity(simple);
            }
        });
*/
        // tuka implementacija za button
        findViewById(R.id.action_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(MainActivity.this,AddProductActivity.class);
                startActivity(simple);
            }
        });

        findViewById(R.id.action_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(MainActivity.this,AddRecipeActivity.class);
                startActivity(simple);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        pa = new ProductAdapter(getApplicationContext());
        productsInListView = (ListView)findViewById(R.id.listProducts);
        productsInListView.setAdapter(pa);
        pa.notifyDataSetChanged();
        fillProgress();
    }

    private View makeHeader() {
        View header = getLayoutInflater().inflate(R.layout.listview_header, null);
        cpb = (CircularProgressBar) header.findViewById(R.id.cpb);
        cpb.setColor(Color.rgb(112, 206, 224));
        Data d = new Data(this);
        int progress = (int) (100.0/d.getLimit() * d.getBudget());
        cpb.setProgressWithAnimation(progress, 2500);

        limit = (TextView) header.findViewById(R.id.tvLimit);
        budget = (TextView) header.findViewById(R.id.tvBudget);

        limit.setText("Limit: " + d.getLimit());
        budget.setText("Budget: " + d.getBudget());

        return header;
    }

    private void fillProgress(){
        Data d = new Data(this);
        int progress = (int) (100.0/d.getLimit() * d.getBudget());
        cpb.setProgressWithAnimation(progress, 2500);
        limit.setText("Лимит: " + d.getLimit());
        budget.setText("Буџет: " + d.getBudget());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      if (id == R.id.action_logout) {
            Data d = new Data(this);
            d.saveCredentials("", "", false);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, MarketActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, MyRecipesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, MyBudgetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ProductAdapter extends BaseAdapter {

        ArrayList<Product> products;

        ProductAdapter(Context ctx) {
            Data d = new Data(ctx);
            products = new ArrayList<>();
            ArrayList<Product> p = d.getAllProducts();
            for (Product p1: p) {
                if (p1.Quantity != -66)
                    products.add(p1);
            }
        }

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
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.list_view, arg2, false);

            TextView name = (TextView)arg1.findViewById(R.id.textView1);
            TextView quan = (TextView)arg1.findViewById(R.id.textView2);
            ImageView image = (ImageView) arg1.findViewById(R.id.image_view);
            TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);
            Product product = products.get(arg0);


            if (product.Quantity == 0.0) {
                image.setImageResource(R.drawable.ic_album_red);
            }

            name.setText(product.Name);
            quan.setText("Количество: " + Double.toString(product.Quantity));
            if(product.IsAvailable) {
                name.setTypeface(null, Typeface.BOLD);
            }


            // image.setImageDrawable(drawable);

            return arg1;
        }
    }



}
