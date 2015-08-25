package com.zuccessful.atlas;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zuccessful.atlas.data.AtlasDbHelper;

public class AtlasActivity extends AppCompatActivity implements AtlasActivityFragment.CallbackInterface {

    FragmentManager manager;
    private String AF1_TAG = "AtlasFrag1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atlas);

        manager = getSupportFragmentManager();

        AtlasActivityFragment atlasFragment = new AtlasActivityFragment();
        manager.beginTransaction().add(R.id.container, atlasFragment, AF1_TAG).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atlas, menu);
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

    @Override
    public void sendText(int playerNum, String text) {
        AtlasActivityFragment frag = (AtlasActivityFragment) manager.findFragmentByTag(AF1_TAG);
        int turn;
        if (playerNum == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
        frag.setPrevText(turn, text);
    }
}