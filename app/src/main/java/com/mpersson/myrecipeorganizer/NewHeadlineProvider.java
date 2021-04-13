package com.mpersson.myrecipeorganizer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewHeadlineProvider extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mTextView;

    NewHeadlineProvider(TextView headlineView) {
        mTextView = new WeakReference<>(headlineView);
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            Log.i("Response Code: ", Integer.toString(responseCode));
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    result += inputLine;
                }
                br.close();
            } else {
                Log.i("Error: ", "You have exceeded the daily access limit! Please Try again tomorrow.");
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Log.i("downloaded website content",s);

        JSONObject jsonObject = null;
        JSONObject articleObject = null;
        int numberOfTopNews;
        try {
            jsonObject = new JSONObject(s);
            if (!jsonObject.isNull("articles")) {
                String articleString = jsonObject.getString("articles");
                JSONArray articleArray = new JSONArray(articleString);
                numberOfTopNews = 10;
                if (articleArray.length() < 10) {
                    numberOfTopNews = articleArray.length();
                }
                for (int i = 0; i < numberOfTopNews; i++) {
                    articleObject = new JSONObject(articleArray.getString(i));
                    if (!articleObject.isNull("title") && !articleObject.isNull("url")) {
                        mTextView.get().setText(articleObject.getString("title"));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
