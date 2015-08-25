package com.zuccessful.atlas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zuccessful.atlas.data.AtlasDbHelper;

public class AtlasActivityFragment extends Fragment {
    private static int playerTurn = 1;
    CallbackInterface prev = null;
    private TextView prevWordText;
    private TextView playerText;
    private Button submitWord;
    private EditText answerText;
    private String enteredWord;
    private AtlasDbHelper dbHelper;

    public AtlasActivityFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        prev = (CallbackInterface) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atlas, container, false);
        dbHelper = new AtlasDbHelper(getActivity());

        submitWord = (Button) view.findViewById(R.id.submitWord);
        answerText = (EditText) view.findViewById(R.id.answerText);
        prevWordText = (TextView) view.findViewById(R.id.prev_word);
        playerText = (TextView) view.findViewById(R.id.playerText);

        submitWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                enteredWord = answerText.getText().toString();
                String enteredWordFormat = enteredWord.trim().toLowerCase();
                if (true) {                                     //dbHelper.checkWord(enteredWordFormat)
                    answerText.setText("");
                    prev.sendText(playerTurn, enteredWord);
                } else {
                    Log.e("TAG", "Not valid");
                    Toast.makeText(getActivity(), "Not a Valid Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prevWordText.setText("Game Starts");
        return view;
    }

    public void setPrevText(int playerNum, String text) {
        playerTurn = playerNum;

        prevWordText.setText(text);
        playerText.setText("Player " + (playerTurn));
    }

    public interface CallbackInterface {
        void sendText(int playerNum, String text);
    }
}
