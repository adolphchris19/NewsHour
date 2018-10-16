package com.adolphchris.android.newshour.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.adolphchris.android.newshour.UI.NewsFeed;
import com.adolphchris.android.newshour.Utils.FashionUtils;
import com.adolphchris.android.newshour.Utils.HomeUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HomeLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = HomeLoader.class.getSimpleName();

    public HomeLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: HomeLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: HomeLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: HomeLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = HomeUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = HomeUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = HomeUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

