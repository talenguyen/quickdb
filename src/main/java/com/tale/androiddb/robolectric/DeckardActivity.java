package com.tale.androiddb.robolectric;

import android.app.Activity;
import android.os.Bundle;

import com.tale.androiddb.R;

public class DeckardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deckard);
    }
}
