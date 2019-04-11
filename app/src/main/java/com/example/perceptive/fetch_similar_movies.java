
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

public class fetch_similar_movies extends AsyncTask<Void, Void, Void> {

    String topic;
    public static String api_key = "b3efb5210f82b9a29217ab85a8e36324";

    public fetch_similar_movies(String topic) {
        this.topic = topic;
    }

    static String data = "";
    public static String arr1[];

    @Override
    protected Void doInBackground(Void... voids) {
        arr1 = new String[10];
        try {
            topic.trim();
            topic = topic.replace(" ", "%20");
            String moviedb = "https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+topic+"&page=1";
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
                id  = result.get("id").toString();

                moviedb = "https://api.themoviedb.org/3/movie/"+id+"/similar?api_key="+api_key+"&language=en-US&page=1";
                urlForGetRequest = new URL(moviedb);
                readLine = null;
                conection = (HttpURLConnection) urlForGetRequest.openConnection();
                responseCode = conection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                    response = new StringBuffer();

                    while ((readLine = in .readLine()) != null) {
                        response.append(readLine);
                        data = data + readLine;
                    } in .close();
                    myResponse = new JSONObject(response.toString());
                    resultsArray = (JSONArray) myResponse.get("results");
                    for (int i = 0; i<10; i++){
                        result = (JSONObject) resultsArray.get(i);
                        arr1[i] = result.get("original_title").toString();
                    }

                } else {
                    System.out.println("GET NOT WORKED");
                }
            } else {
                System.out.println("GET NOT WORKED");
            }
        }
        catch (Exception e) {
            arr1= new String[]{"New Number. Who dis?"};
            Log.d("ErrorCustom", e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        searchActivity.update_list(arr1);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}

