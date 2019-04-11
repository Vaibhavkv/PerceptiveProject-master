package com.example.perceptive;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch_details_movies extends AsyncTask<Void, Void, Void> {

    public static String api_key = "b3efb5210f82b9a29217ab85a8e36324";
    public static String data = "";
    public String artist_str;

    public fetch_details_movies(String artist_str) {
        this.artist_str = artist_str;
    }

    public static class artist_details {
        String name = "";
        String image_url = "";
        String summary = "";
        String listeners = "";
    };

    public static artist_details a2 = new artist_details();

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            artist_str.trim();
            artist_str = artist_str.replace(" ", "%20");
            String moviedb = "https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+artist_str+"&page=1";
            // String moviedb = "https://api.themoviedb.org/3/search/movie?api_key=b3efb5210f82b9a29217ab85a8e36324&query=the+avengers&page=1";

            String id = "";
            URL urlForGetRequest = new URL(moviedb);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();

                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                    data = data + readLine;
                } in .close();

                JSONObject myResponse = new JSONObject(response.toString());
                JSONArray resultsArray = (JSONArray) myResponse.get("results");
                JSONObject result = (JSONObject) resultsArray.get(1);
                a2.name = result.get("title").toString();
                a2.image_url = "https://image.tmdb.org/t/p/w500"+result.get("poster_path").toString();
                a2.summary = result.get("overview").toString();
                a2.listeners = result.get("vote_average").toString();
                // System.out.println(id);
            } else {
                System.out.println("GET NOT WORKED");
            }

        }catch (Exception e) {

        }
        return null;
    }
}
