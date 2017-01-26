package com.toshevski.nemesis.fridge.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.R;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
                Intent simple = new Intent(AddProductActivity.this,MainActivity.class);
                startActivity(simple);
            }
        });

        findViewById(R.id.action_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simple = new Intent(AddProductActivity.this,AddRecipeActivity.class);
                startActivity(simple);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Data d = new Data(getApplicationContext());
            EditText tmp=(EditText) findViewById(R.id.editText1);
            EditText tmp2=(EditText) findViewById(R.id.editText2);
            if(!tmp.getText().toString().matches("") && !tmp2.getText().toString().matches("")) {
                Product p = new Product("", 0, false);
                p.Name = tmp.getText().toString();
                p.Quantity = Double.parseDouble(tmp2.getText().toString());
                d.insertIntoProducts(p);
                tmp.setText("");
                tmp2.setText("");
                finish();
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
