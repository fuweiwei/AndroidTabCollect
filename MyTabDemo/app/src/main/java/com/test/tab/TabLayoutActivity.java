package com.test.tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuweiwei on 2015/12/19.
 */
public class TabLayoutActivity extends android.support.v4.app.FragmentActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTabList = new ArrayList<>();
    private  List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        initView();
    }

    private  void initView(){
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabList.add(" tab1 ");
        mTabList.add(" tab2 ");
        mTabList.add(" tab3 ");
        mTabList.add(" tab4 ");
        mTabList.add(" tab5 ");
        mTabList.add(" tab6 ");
        mTabList.add(" tab7 ");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，MODE_SCROLLABLE 可滚动，MODE_FIXED不可滚动
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(4)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(5)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(6)));
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        Fragment6 fragment6 = new Fragment6();
        Fragment7 fragment7 = new Fragment7();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mFragments.add(fragment4);
        mFragments.add(fragment5);
        mFragments.add(fragment6);
        mFragments.add(fragment7);
        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), mFragments, mTabList);
        mViewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器
    }
    public class TabFragmentAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mTitles;

        public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
    @Override
    public void onClick(View v) {

    }
}
