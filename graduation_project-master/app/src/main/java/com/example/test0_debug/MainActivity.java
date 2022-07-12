package com.example.test0_debug;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dk.view.patheffect.PathTextView;
import com.example.test0_debug.fragment.MyFragmentPagerAdapter;
import com.example.test0_debug.fragment.ReviewFragment;
import com.example.test0_debug.fragment.SettintFragment;
import com.example.test0_debug.fragment.StatisticFragment;
import com.example.test0_debug.fragment.BookListFragment;
import com.example.test0_debug.tools.OtherTools;
import com.example.test0_debug.tools.db.DBHelper;
import com.example.test0_debug.tools.ui.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DBHelper.getThemeNight(this, 1) == DBHelper.THEME_NIGHT) {
            StatusBarUtil.setStatusBarMode(this, false, R.color.status_bar_color);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.status_bar_color);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (OtherTools.getLoginStatus(this))
            initFunctionView();
        else
            initLoginView();
    }

    private void initLoginView() {
        setContentView(R.layout.login_main);
        PathTextView mPathTextView = findViewById(R.id.path);
        mPathTextView.init("welcome");
        mPathTextView.setPaintType(PathTextView.Type.SINGLE);
        mPathTextView.setTextColor(getResources().getColor(R.color.black));
        mPathTextView.setTextWeight(5);
        mPathTextView.setClickable(true);
        mPathTextView.setDuration(4000);
        mPathTextView.setShadow(10, 10, 10, getResources().getColor(R.color.black));
        Button bt_login=findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

    }

    private void initFunctionView() {
        setContentView(R.layout.function_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Intent startIntent = new Intent(this, DBService.class);
        startIntent.putExtra(DBHelper.SERVICE_SIGN, DBHelper.SET_EBBINGHAUS_MEMORY);
        //startIntent.putExtra("user_id",DBHelper.getBookClassId(this,1));
        this.startService(startIntent);
        // find view
        mViewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        // init fragment
        ReviewFragment reviewFragment = new ReviewFragment();
        SettintFragment settintFragment = new SettintFragment();
        StatisticFragment statisticFragment = new StatisticFragment();
        BookListFragment wordListFragment = new BookListFragment();
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(reviewFragment);
        mFragments.add(statisticFragment);
        mFragments.add(wordListFragment);
        mFragments.add(settintFragment);
        // init view pager
        FragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mViewPager.setOffscreenPageLimit(4);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (OtherTools.getLoginStatus(this))
            mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private final RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);

                }
            }
        }
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_login:
                OtherTools.setLoginStatus(this,true);
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                break;

            default:
                break;
        }
    }
}