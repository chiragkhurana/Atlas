package com.zuccessful.atlas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chirag Khurana on 31-Aug-15.
 */
public class FetchCountryTask extends AsyncTask<Void, Void, Void> {
    private static final String LOG_TAG = FetchCountryTask.class.getSimpleName();
    Context context;
    ProgressDialog pd;

    FetchCountryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setIndeterminate(false);
        pd.setMessage("Fetching Country Names...");
        pd.setCancelable(true);
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String countriesJsonString = null;

        try {
            final String COUNTRIES_BASE_URL = "https://restcountries.eu/rest/v1/all";

            URL url = new URL(COUNTRIES_BASE_URL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            countriesJsonString = buffer.toString();
            Log.i(LOG_TAG, countriesJsonString);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
    }
}
