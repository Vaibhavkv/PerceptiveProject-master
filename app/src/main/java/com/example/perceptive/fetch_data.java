
package com.example.perceptive;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch_data extends AsyncTask<Void, Void, Void> {

    String topic;

    public fetch_data(String topic) {
        this.topic = topic;
    }

    static String data = "";
    public static String arr[] = new String[10];

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            String lastfm1 = "http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=";
            String lastfm2 = "&limit=10&api_key=d4d8ce6b7f878903fd54602d00a66f0d&format=json";
            topic.trim();
            topic.replace(" ", "%20");
            String lastfm = lastfm1 + topic + lastfm2;

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
                JSONObject myResponse1 = new JSONObject(myResponse.get("similarartists").toString());
                JSONArray artistsArray = (JSONArray) myResponse1.get("artist");
                for (int i = 0; i<artistsArray.length(); i++){
                    JSONObject artist = (JSONObject) artistsArray.get(i);
                    arr[i] = artist.get("name").toString();
                }
            } else {
                System.out.println("GET NOT WORKED");
            }
        }
        catch (Exception e) {
            Log.d("ErrorCustom", e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        searchActivity.update_list(arr);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}

