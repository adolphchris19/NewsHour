package com.adolphchris.android.newshour.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adolphchris.android.newshour.Fragments.Business;
import com.adolphchris.android.newshour.Fragments.Fashion;
import com.adolphchris.android.newshour.Fragments.Home;
import com.adolphchris.android.newshour.Fragments.Science;
import com.adolphchris.android.newshour.Fragments.Sports;
import com.adolphchris.android.newshour.Fragments.World;
import com.adolphchris.android.newshour.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new Home();
        }else if (position == 1){
            return new World();
        }else if (position == 2){
            return new Business();
        }else if (position == 3){
            return new Science();
        }else if (position == 4){
            return new Sports();
        }else{
            return new Fashion();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 0){
            return mContext.getString(R.string.home);
        }else if (position == 1){
            return mContext.getString(R.string.world);
        }else if (position == 2){
            return mContext.getString(R.string.business);
        }else if (position == 3){
            return mContext.getString(R.string.science);
        }else if (position == 4){
            return mContext.getString(R.string.sports);
        }else{
            return mContext.getString(R.string.fashion);
        }
    }
}
