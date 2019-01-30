package com.i550.loaderquakestats;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.content.Loader;
import android.text.TextUtils;
import android.util.Log;

public class QuakeLoader extends Loader<String> {

    private static final String LOG_TAG = "QuakeLoader";

    private String paramFromBundle;
    private IncrediblySmartAsyncTask task;

    public QuakeLoader(@NonNull Context context, Bundle args) {
        super(context);
        Log.d(LOG_TAG, hashCode() + " create TimeLoader");              //log
        if (args != null)                                                       // if Bundle non-empty
            paramFromBundle = args.getString(MainActivity.PARAM_TAG);
        if (TextUtils.isEmpty(paramFromBundle))
            paramFromBundle = "no param";
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");              //log
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(LOG_TAG, hashCode() + " onForceLoad");              //log
        if (task != null)
            task.cancel(true);
        task = new IncrediblySmartAsyncTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramFromBundle);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onStopLoading");              //log
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");              //log
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.d(LOG_TAG, hashCode() + " onReset");              //log
    }

    void getResultFromTask(String result) {                        //RESULT!!
        deliverResult(result);
    }

    class IncrediblySmartAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            Log.d(LOG_TAG, QuakeLoader.this.hashCode() + " doInBackground");
            //WORKS TODO
            String result = "result tasks";


            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG, QuakeLoader.this.hashCode() + " onPostExecute "
                    + result);
            getResultFromTask(result);
        }
    }
}
