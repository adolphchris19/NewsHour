package com.adolphchris.android.newshour.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.adolphchris.android.newshour.UI.NewsFeed;
import com.adolphchris.android.newshour.Utils.HomeUtils;
import com.adolphchris.android.newshour.Utils.WorldUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WorldLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = WorldLoader.class.getSimpleName();

    public WorldLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: WorldLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: WorldLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: WorldLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = WorldUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = WorldUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = WorldUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

