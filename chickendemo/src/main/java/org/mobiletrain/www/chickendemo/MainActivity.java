package org.mobiletrain.www.chickendemo;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import fragmentbean.ChickenFragment;
import fragmentbean.HomeFragment;
import fragmentbean.MoreFragment;
import fragmentbean.NumFragment;
import fragmentbean.SearchFragment;


public class MainActivity extends ActionBarActivity {

    private ArrayList<Fragment> fragments;
    private ViewPager pager;
    private RadioGroup group;
    private android.support.v7.app.ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar=getSupportActionBar();
        bar.setDefaultDisplayHomeAsUpEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("玩转厨房");
        pager = (ViewPager) findViewById(R.id.pager_main);
        fragments = new ArrayList<Fragment>();
        group= (RadioGroup) findViewById(R.id.group_main);
        fragments.add(new HomeFragment());
        fragments.add(new ChickenFragment());
        fragments.add(new MoreFragment());
        fragments.add(new NumFragment());
        fragments.add(new SearchFragment());
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton butoon= (RadioButton) group.getChildAt(position);
                butoon.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
