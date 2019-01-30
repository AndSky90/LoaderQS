package com.i550.loaderquakestats;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    private static final String LOG_TAG = "QuakeLoaderMainActivity";
    public static final String PARAM_TAG = "ParamFromBundle";
    public static final int GLOBAL_LOADER_ID = 1;
    public static final int DUEL_LOADER_ID = 2;
    public static final int TDM_LOADER_ID = 3;
    public static final int STATS_LOADER_ID = 4;
    public static final int SUMMARY_LOADER_ID = 5;

    TextView textViewGlobal, textViewDuel, textViewTdm, textViewStats, textViewSummary;
    Button buttonGlobal, buttonDuel, buttonTdm, buttonStats, buttonSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewGlobal = findViewById(R.id.textViewGlobal);
        textViewDuel = findViewById(R.id.textViewDuel);
        textViewTdm = findViewById(R.id.textViewTdm);
        textViewStats = findViewById(R.id.textViewStats);
        textViewSummary = findViewById(R.id.textViewSummary);
        buttonGlobal = findViewById(R.id.buttonGlobal);
        buttonDuel = findViewById(R.id.buttonDuel);
        buttonTdm = findViewById(R.id.buttonTdm);
        buttonStats = findViewById(R.id.buttonStats);
        buttonSummary = findViewById(R.id.buttonSummary);


        Bundle quakeLoaderArgs = new Bundle();
        quakeLoaderArgs.putString(PARAM_TAG, "Stats");
        Loader ql = getLoaderManager().initLoader(STATS_LOADER_ID, quakeLoaderArgs, this);

        buttonStats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Loader<String> loader = getLoaderManager().getLoader(STATS_LOADER_ID);
                loader.forceLoad();
            }
        });
    }

    @NonNull
    @Override
    public  Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        Loader<String> loader = new QuakeLoader(this, bundle);

        Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());

        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()  + ", result = " + s);
        textViewStats.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }
}
