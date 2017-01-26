package com.toshevski.nemesis.fridge.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.R;

import org.w3c.dom.Text;

public class MyBudgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_budget);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button zachuvaj= (Button) findViewById(R.id.button);
        Button promenilimit= (Button) findViewById(R.id.button1);
        Button dodadi=(Button) findViewById(R.id.button2);


        Data d = new Data(getApplicationContext());
        EditText tmp=(EditText) findViewById(R.id.editText1);
        tmp.setText(d.getLimit()+" ден");
        TextView txt=(TextView) findViewById(R.id.textView7);
        txt.setText(d.getBudget()+" ден");

        promenilimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tmp=(EditText) findViewById(R.id.editText1);
                tmp.setEnabled(true);
                tmp.setText("");
                tmp.setFocusable(true);
            }
        });

        zachuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tmp=(EditText) findViewById(R.id.editText1);

                Data d = new Data(getApplicationContext());
                if(TextUtils.isDigitsOnly(tmp.getText())) {
                    String k=tmp.getText().toString();
                    d.setLimit(Integer.parseInt(k));
                    tmp.setText(d.getLimit() + " ден");
                    tmp.setEnabled(false);
                }

            }
        });

        dodadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data d = new Data(getApplicationContext());
                EditText tmp=(EditText) findViewById(R.id.editText2);
                TextView txt=(TextView) findViewById(R.id.textView7);

                if(TextUtils.isDigitsOnly(tmp.getText()) && (tmp.getText().toString()!= "")) {
                    String k=tmp.getText().toString();
                    d.increaseBudget(Integer.parseInt(k));
                    tmp.setText("");
                    txt.setText(d.getBudget()+" ден");
                }
            }
        });
    }

}
