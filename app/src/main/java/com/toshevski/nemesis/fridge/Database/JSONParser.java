package com.toshevski.nemesis.fridge.Database;

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
        this.baseURL = "http://localhost:";
    }

    public ArrayList<T> getList(String urlLink) {
        try {
            URL url = new URL(baseURL + "" + PORT + urlLink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = rd.readLine();
            System.out.println("Result: " + line);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
