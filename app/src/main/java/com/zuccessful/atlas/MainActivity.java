package com.zuccessful.atlas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isInternetAvailable(this)) {
            FetchCountryTask syncTask = new FetchCountryTask(this);
            syncTask.execute();
        } else {
            Toast.makeText(this, "Internet Connectivity Unavailable", Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayer(View view) {
        Intent intent = new Intent(this, AtlasActivity.class);
        startActivity(intent);
    }

    public void startDoublePlayer(View view) {
        Intent intent = new Intent(this, AtlasActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isInternetAvailable(Context context) {
        boolean isInternetAvailable = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && (networkInfo.isConnected())) {
                isInternetAvailable = true;
            }
        } catch (Exception exception) {
            // Do Nothing
        }
        return isInternetAvailable;
    }
}
