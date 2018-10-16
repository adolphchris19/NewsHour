package com.adolphchris.android.newshour.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adolphchris.android.newshour.Adapter.NewsFeedAdapter;
import com.adolphchris.android.newshour.Loaders.SportsLoader;
import com.adolphchris.android.newshour.R;
import com.adolphchris.android.newshour.UI.NewsFeed;

import java.util.ArrayList;
import java.util.List;


public class Sports extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsFeed>> {

    ListView listView;
    TextView mEmptyStateTextView;
    ProgressBar loadingIndicator;

    private NewsFeedAdapter mAdapter;
    private static int LOADER_ID = 0;
    public static final String LOG_TAG = Sports.class.getName();

    public Sports() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        listView = view.findViewById(R.id.listView);
        mEmptyStateTextView = view.findViewById(R.id.no_data);
        loadingIndicator = view.findViewById(R.id.progressBar);

        listView.setEmptyView(mEmptyStateTextView);
        mAdapter = new NewsFeedAdapter(getActivity(), new ArrayList<NewsFeed>());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsFeed currentNewsFeed = mAdapter.getItem(position);

                Uri newsFeedUri = Uri.parse(currentNewsFeed.getUrl());
                if (newsFeedUri != null) {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsFeedUri);
                    startActivity(websiteIntent);
                }
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        return view;
    }

    @Override
    public Loader<List<NewsFeed>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: SportFragment onCreateLoader is called");
        return new SportsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsFeed>> loader, List<NewsFeed> data) {
        Log.i(LOG_TAG, "TEST: SportFragment onLoaderFinished is called");
        loadingIndicator = getView().findViewById(R.id.progressBar);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addAll(data);
        mEmptyStateTextView.setText(R.string.no_news_found);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsFeed>> loader) {
        Log.i(LOG_TAG, "TEST: SportFragment onLoaderReset is called");
        mAdapter.clear();
    }
}
