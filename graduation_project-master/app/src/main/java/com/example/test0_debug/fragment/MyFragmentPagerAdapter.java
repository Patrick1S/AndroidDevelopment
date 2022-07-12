package com.example.test0_debug.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mList;



    public MyFragmentPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> mFragments) {
        super(supportFragmentManager);
        mList=mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mList == null ? null : this.mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList == null ? 0 : this.mList.size();
    }
}