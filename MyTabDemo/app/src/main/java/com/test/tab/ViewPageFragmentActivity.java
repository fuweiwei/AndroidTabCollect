package com.test.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuweiwei on 2015/12/19.
 */
public class ViewPageFragmentActivity extends android.support.v4.app.FragmentActivity implements View.OnClickListener{
    private TextView mTv1,mTv2,mTv3;
    private View mView1,mView2,mView3;
    private RelativeLayout mRel1,mRel2,mRel3;
    public static final int TAB1 = 0, TAB2 = 1,TAB3 = 2;
    private ViewPager mViewPage;
    private FragmentPagerAdapter mAdapter;
    private  List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_fragment);
        initView();

    }
    private void initView(){
        mTv1 = (TextView) findViewById(R.id.tab1);
        mTv2 = (TextView) findViewById(R.id.tab2);
        mTv3 = (TextView) findViewById(R.id.tab3);
        mView1 = findViewById(R.id.line1);
        mView2 = findViewById(R.id.line2);
        mView3 = findViewById(R.id.line3);
        mRel1 = (RelativeLayout) findViewById(R.id.Rel1);
        mRel2 = (RelativeLayout) findViewById(R.id.Rel2);
        mRel3 = (RelativeLayout) findViewById(R.id.Rel3);
        mViewPage = (ViewPager) findViewById(R.id.viewpager);
        mRel1.setTag(TAB1);
        mRel2.setTag(TAB2);
        mRel3.setTag(TAB3);
        mRel1.setOnClickListener(this);
        mRel2.setOnClickListener(this);
        mRel3.setOnClickListener(this);
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPage.setAdapter(mAdapter);
        //设置viewpage滑动监听
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBtn(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPage.setCurrentItem(TAB1);
    }

    /**
     * tab标签点击效果
     * @param tag
     */
    private void setBtn(int tag) {
        if (tag == TAB1) {
            mTv1.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTv2.setTextColor(Color.BLACK);
            mTv3.setTextColor(Color.BLACK);
            mView1.setVisibility(View.VISIBLE);
            mView2.setVisibility(View.INVISIBLE);
            mView3.setVisibility(View.INVISIBLE);
        } else if (tag == TAB2) {
            mTv2.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTv1.setTextColor(Color.BLACK);
            mTv3.setTextColor(Color.BLACK);
            mView2.setVisibility(View.VISIBLE);
            mView1.setVisibility(View.INVISIBLE);
            mView3.setVisibility(View.INVISIBLE);
        }else if (tag == TAB3) {
            mTv3.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTv1.setTextColor(Color.BLACK);
            mTv2.setTextColor(Color.BLACK);
            mView3.setVisibility(View.VISIBLE);
            mView1.setVisibility(View.INVISIBLE);
            mView2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        mViewPage.setCurrentItem(tag);
    }
}
