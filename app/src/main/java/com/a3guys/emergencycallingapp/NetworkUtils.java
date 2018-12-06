package com.a3guys.emergencycallingapp;


import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import android.net.Uri.Builder;

public class NetworkUtils {

    final static String base_url = "https://emergency-calling-app.firebaseio.com/Users";

    final static String PARAM_SOURCE = "source";
    final static String source = "the-next-web";

    final static String PARAM_SORT = "sortBy";
    final static String sortby = "top";

    final static String PARAM_KEY = "apiKey";

    public static URL buildUrl (String apikey) {
        Uri builtUri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, source)
                .appendQueryParameter(PARAM_SORT, sortby)
                .appendQueryParameter(PARAM_KEY, apikey)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    //copied method from powerpoint
    public static String getResponseFromHttpUrl( URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}