package com.example.alarm.diet_ver1.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.alarm.diet_ver1.Activities.Fragment1.Fragment1_Banner1;
import com.example.alarm.diet_ver1.Activities.Fragment1.Fragment1_Banner2;
import com.example.alarm.diet_ver1.Activities.Fragment1.Fragment1_Banner3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments;
    private static int NUM_ITEMS = 3;

    public ViewPagerAdapter(FragmentManager fragmentManager){

        super(fragmentManager);
        fragments = new Fragment[NUM_ITEMS];

        fragments[0] = new Fragment1_Banner1();
        fragments[1] = new Fragment1_Banner2();
        fragments[2] = new Fragment1_Banner3();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }


}
