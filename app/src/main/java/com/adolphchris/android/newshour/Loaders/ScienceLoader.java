package com.adolphchris.android.newshour.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.adolphchris.android.newshour.UI.NewsFeed;
import com.adolphchris.android.newshour.Utils.HomeUtils;
import com.adolphchris.android.newshour.Utils.ScienceUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ScienceLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = ScienceLoader.class.getSimpleName();

    public ScienceLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: scienceLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: ScienceLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: ScienceLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = ScienceUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = ScienceUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = ScienceUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

