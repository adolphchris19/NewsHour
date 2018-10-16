package com.adolphchris.android.newshour.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.adolphchris.android.newshour.UI.NewsFeed;
import com.adolphchris.android.newshour.Utils.FashionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FashionLoader extends AsyncTaskLoader<List<NewsFeed>> {

    public static final String LOG_TAG = FashionLoader.class.getSimpleName();

    public FashionLoader(Context context) {
        super(context);
        Log.i(LOG_TAG, "THIS: FashionLoader constructor is called");
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "THIS: FashionLoader onStartLoading is called");
        forceLoad();
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        Log.i(LOG_TAG, "THIS: FashionLoader loadInBackground is called");
        Log.i(LOG_TAG, "TEST:  fetchNewsFeed called");
        URL url = FashionUtils.createUrl();
        String jsonResponse = null;

        try {
            jsonResponse = FashionUtils.makeHttpConnection(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "fetchNewsFeed: problem fetchNewsFeed", e);
        }
        List<NewsFeed> newsFeeds = FashionUtils.extractFeatureFromJson(jsonResponse);
        return newsFeeds;
    }
}

