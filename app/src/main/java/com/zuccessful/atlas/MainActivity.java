package com.zuccessful.atlas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String SELECTION_EXTRA = "com.zuccessful.atlas.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayer(View view) {
        Intent intent = new Intent(this, AtlasActivity.class);
        intent.putExtra(SELECTION_EXTRA, "single");
        startActivity(intent);
    }

    public void startDoublePlayer(View view) {
        Intent intent = new Intent(this, AtlasActivity.class);
        intent.putExtra(SELECTION_EXTRA, "double");
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
}
