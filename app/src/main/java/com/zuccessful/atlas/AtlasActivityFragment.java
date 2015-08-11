package com.zuccessful.atlas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AtlasActivityFragment extends Fragment {
    TextView tv;

    public AtlasActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atlas, container, false);
        tv = (TextView) view.findViewById(R.id.prev_word);

        tv.setText(getActivity().getIntent().getStringExtra(MainActivity.SELECTION_EXTRA));
        return view;
    }
}
