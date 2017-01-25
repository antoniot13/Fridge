package com.toshevski.nemesis.fridge.View;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.toshevski.nemesis.fridge.Database.Data;
import com.toshevski.nemesis.fridge.Database.StaticData;
import com.toshevski.nemesis.fridge.Database.TrackGPS;
import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MarketActivity extends AppCompatActivity {

    public MarketAdapter ma;
    public TrackGPS gps;
    private ArrayList<Market> places;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        // Location impelemntation
       gps = new TrackGPS(MarketActivity.this);
        Location l = new Location("");
        l.setLatitude(gps.getLatitude());
        l.setLongitude(gps.getLongitude());
        new GetResults().execute(l);

        //Implementation of adapter
        places = new ArrayList<>();
        ma = new MarketAdapter(places);
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


    class GetResults extends AsyncTask<Location, Void, Void> {

        public ArrayList<Market> places = new ArrayList<>();

        @Override
        protected void onPostExecute(Void aVoid) {

            ma.update(places);
            ma.notifyDataSetChanged();

            //Data d = new Data(getApplicationContext());
            //d.deleteMarkets();
          /*  for(Market place : places) {
                d.insertIntoMarket(place);
            }*/
        }

        @Override
        protected Void doInBackground(Location... params) {
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                        "?location=" +
                        Double.toString(params[0].getLatitude()) +
                        "," +
                        Double.toString(params[0].getLongitude()) +
                        "&radius=1000&types=grocery_or_supermarket&" +
                        "key=AIzaSyBa_2n743PwV8g2bHy1HflGwm4-AEw0wQY");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line = rd.readLine();

                while (line != null) {
                    sb.append(line);
                    line = rd.readLine();
                }

                parseJSON(sb.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private void parseJSON(String line) {

            try {
                JSONObject response = new JSONObject(line);

                JSONArray results = response.getJSONArray("results");

                for (int i = 0; i < results.length(); ++i) {
                    JSONObject result = results.getJSONObject(i);
                    JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
                    double lat = location.getDouble("lat");
                    double lng = location.getDouble("lng");

                    String name = result.getString("name");
                    Location l = new Location("");
                    l.setLatitude(lat);
                    l.setLongitude(lng);
                    Market p = new Market(name, l);

                    places.add(p);
                    Log.i("Iminja:", name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class MarketAdapter extends BaseAdapter {

        public ArrayList<Market> markets;

        MarketAdapter(ArrayList<Market> newMarkets) {
            //Data d = new Data(ctx);
            markets = newMarkets;
        }

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

        public void update(ArrayList<Market> updatedMarkets) {
            markets = updatedMarkets;

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
