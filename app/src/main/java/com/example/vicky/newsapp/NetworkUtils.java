package com.example.vicky.newsapp;

import android.net.Uri;
import android.util.Log;

import com.example.vicky.newsapp.data.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vicky on 6/19/17.
 */

public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String GITHUB_BASE_URL =
            "https://newsapi.org/v1/articles";
    public static final String PARAM_QUERY = "source";

    public static final String PARAM_SORT = "sortBy";
    public static final String PARAM_API_KEY= "apiKey";

    public static URL makeURL() {
        Uri uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,"the-next-web").appendQueryParameter(PARAM_SORT,"latest").appendQueryParameter(PARAM_API_KEY,"0bd77307a2da4ea6b3bc34da1e964f1d").build();


        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");
            String result = (input.hasNext()) ? input.next() : null;
            return result;

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    public static ArrayList<Article> parseJSON(String json) throws JSONException {
        ArrayList<Article> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray items = main.getJSONArray("articles");
        String imgUrl = null;

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String title = item.getString("title");
            String author = item.getString("author");
            String publishedDate = item.getString("publishedAt");
            String description = item.getString("description");
            String url = item.getString("url");
            String urlToImage = item.getString("urlToImage");

            result.add(new Article(author, title, description, url, urlToImage, publishedDate));

        }
        Log.d(TAG, "final articles size: " + result.size());
        return result;
    }


}

