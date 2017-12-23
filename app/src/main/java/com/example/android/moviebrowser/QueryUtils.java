package com.example.android.moviebrowser;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    static String BASE_URL = "https://begorov.me/";
    static String MOVIES_JSON_URL = BASE_URL + "v1/ru/movies.json";

    static String MOVIES_JSON = "{\n" +
            "  \"movies\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"theater_id\": 3,\n" +
            "      \"name\": \"Movie1\",\n" +
            "      \"poster_url\": \"http://via.placeholder.com/350x350\",\n" +
            "      \"spoiler\": \"OMG\",\n" +
            "      \"rating\": 3.5,\n" +
            "      \"buy_ticket_url\": \"http://google.com\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"theater_id\": 2,\n" +
            "      \"name\": \"Movie Two\",\n" +
            "      \"poster_url\": \"http://via.placeholder.com/350x350\",\n" +
            "      \"spoiler\": \"OMGOMG\",\n" +
            "      \"rating\": 6.2,\n" +
            "      \"buy_ticket_url\": \"http://google.com\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 5,\n" +
            "      \"theater_id\": 3,\n" +
            "      \"name\": \"Movie V\",\n" +
            "      \"poster_url\": \"http://via.placeholder.com/350x350\",\n" +
            "      \"spoiler\": \"OMG    ZZZ\",\n" +
            "      \"rating\": 10.0,\n" +
            "      \"buy_ticket_url\": \"http://google.com\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"theaters\": [\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"Победа\",\n" +
            "      \"geo_tag\": \"3333,3\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"name\": \"Синема\",\n" +
            "      \"geo_tag\": \"42,3\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static List<Movie> parse(String json) throws JSONException
    {
        List<Movie> movies = new ArrayList<>();
        Gson gson = new Gson();

        JSONObject root = new JSONObject(json);
        JSONArray moviesJson = root.getJSONArray("movies");

        for (int i = 0; i < moviesJson.length(); i++) {
            Movie movie = gson.fromJson(moviesJson.getJSONObject(i).toString(), Movie.class);
            movies.add(movie);
        }

        return movies;
    }

    public static List<Movie> fetchMovieData(String urlString) {
        List<Movie> movies = null;

        if (TextUtils.isEmpty(urlString)) {
            return null;
        }

        try {
            URL url = new URL(urlString);
            String jsonString = makeHttpRequest(url);
            movies = parse(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
