package com.toshevski.nemesis.fridge.Database;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JSONParser<T> {
    private int PORT;
    private String baseURL;

    public JSONParser(int PORT) {
        this.PORT = PORT;
        this.baseURL = "http://192.168.0.101:";
    }

    public ArrayList<T> getList(String urlLink) {
        try {
            URL url = new URL(baseURL + "" + PORT + urlLink);
            Log.d("INFOOO", url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            Log.d("INFOOO", Integer.toString(connection.getResponseCode()));

            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = rd.readLine();
            Log.d("INFOOO", line);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
