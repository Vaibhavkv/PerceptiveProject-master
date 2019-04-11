package com.example.perceptive;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch_details_artists extends AsyncTask<Void, Void, Void> {

    public static String api_key = "d4d8ce6b7f878903fd54602d00a66f0d";
    public static String data = "";
    public String artist_str;

    public fetch_details_artists(String artist_str) {
        this.artist_str = artist_str;
    }

    public static class artist_details {
        String name = "";
        String image_url = "";
        String summary = "";
        String listeners = "";
    };

    public static artist_details a1 = new artist_details();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d("aaa", "Starting...");
            artist_str = artist_str.trim();
            artist_str = artist_str.replace(" ", "%20");
            artist_str = artist_str.replace("&", "and");
            String lastfm = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="+artist_str+"&api_key="+api_key+"&format=json";
            URL urlForGetRequest = new URL(lastfm);
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
                JSONObject myResponse1 = new JSONObject(myResponse.get("artist").toString());
                a1.name = myResponse1.get("name").toString();
                JSONArray imagesArray = (JSONArray) myResponse1.get("image");
                JSONObject image = (JSONObject) imagesArray.get(2);
                a1.image_url = image.get("#text").toString();
                JSONObject bioResponse = new JSONObject(myResponse1.get("bio").toString());
                a1.summary = bioResponse.get("summary").toString();
                JSONObject statResponse = new JSONObject(myResponse1.get("stats").toString());
                a1.listeners = statResponse.get("listeners").toString();
            } else {
                System.out.println("GET NOT WORKED");
            }
        }catch (Exception e) {

        }
        return null;
    }
}
