package com.adolphchris.android.newshour.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.adolphchris.android.newshour.UI.NewsFeed;
import com.adolphchris.android.newshour.Utils.BusinessUtils;
import com.adolphchris.android.newshour.Utils.SportsUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BusinessLoader  extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = BusinessLoader.class.getSimpleName();

    public BusinessLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: BusinessLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: BusinessLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: BusinessLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = BusinessUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = BusinessUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = BusinessUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

