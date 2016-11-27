package com.toshevski.nemesis.fridge.View;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.R;

import java.util.ArrayList;


public class MarketActivity extends AppCompatActivity {

    MarketAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        //Implementation of adapter
        ma = new MarketAdapter();
        ListView marketsInListView = (ListView)findViewById(R.id.listMarkets);
        marketsInListView.setAdapter(ma);
        ma.notifyDataSetChanged();
        marketsInListView.setAlpha(1);

        marketsInListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("MARKET", "I: " + i);
                Market m = ma.getItem(i);
                String strUri = "http://maps.google.com/maps?q=loc:" + m.Location.getLatitude() +
                        "," + m.Location.getLongitude() + " (" + m.Name + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

    }

    public class MarketAdapter extends BaseAdapter {

        ArrayList<Market> markets = StaticData.getMarkets();

        @Override
        public int getCount() {
            return markets.size();
        }

        @Override
        public Market getItem(int arg0) {
            return markets.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            LayoutInflater inflater = (LayoutInflater) MarketActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.list_view, arg2, false);

            TextView name = (TextView)arg1.findViewById(R.id.textView1);
            TextView quan = (TextView)arg1.findViewById(R.id.textView2);

            Market market = markets.get(arg0);

            name.setText(market.Name);

            return arg1;
        }
    }


}
