# MyTabDemo
标签页大汇总

在android项目中：

这种Tab类型页面越来越多了，在很多主流的app中都有出现，因为这种方式在一个页面尽量多的展示内容，减少了部分的页面跳转，也使内容更清晰有条理，作为程序猿的我们当然不能放过任何好的想法和方法，下面总结下我所经常使用的实现Tab类型页面的方法。

目前我经常使用的3种方式：

1、单纯的使用FragmentManger+Fragment实现

2、使用ViewPage+Fragment实现

3、使用Google在2015 IO大会带来的Material Design设计规范中的TabLayout实现
1、使用FragmentManger+Fragment实现

这里使用FragmentManger对Fragment做一些add、show、hide、attach、detach等事务操作，来实现Tab类型页面

代码如下：


[html] view plain copy

    import android.graphics.Color;  
    import android.os.Bundle;  
    import android.support.v4.app.Fragment;  
    import android.support.v4.app.FragmentManager;  
    import android.support.v4.app.FragmentTransaction;  
    import android.util.SparseArray;  
    import android.view.View;  
    import android.widget.RelativeLayout;  
    import android.widget.TextView;  
      
    /**  
     * Created by fuweiwei on 2015/12/19.  
     */  
    public class FragmentActivity extends android.support.v4.app.FragmentActivity implements View.OnClickListener{  
        private TextView mTv1,mTv2,mTv3;  
        private View mView1,mView2,mView3;  
        private RelativeLayout mRel1,mRel2,mRel3;  
        public static final int TAB1 = 1, TAB2 = 2,TAB3 = 3;  
        private final SparseArray<Fragment> mFragments = new SparseArray<Fragment>();  
        //最后使用的fragment  
        private Fragment mLastFragment;  
      
        @Override  
        protected void onCreate(Bundle savedInstanceState) {  
            super.onCreate(savedInstanceState);  
            setContentView(R.layout.activity_fragment);  
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
            mRel1.setTag(TAB1);  
            mRel2.setTag(TAB2);  
            mRel3.setTag(TAB3);  
            mRel1.setOnClickListener(this);  
            mRel2.setOnClickListener(this);  
            mRel3.setOnClickListener(this);  
            setFragmentIndicator(TAB1);  
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
        private void setFragmentIndicator(int tag) {  
            setBtn(tag);  
            Fragment newInfo = mFragments.get(tag);  
            FragmentManager fmManager = getSupportFragmentManager();  
            //开启一个fragment事务  
            FragmentTransaction transaction = fmManager.beginTransaction();  
            if (mLastFragment != null) {  
                //隐藏正在显示的fragment ，这里也可以用 transaction.detach(mLastFragment) 会将view从UI中移除，执行onDestroyView()  
                transaction.hide(mLastFragment);  
    //          transaction.detach(mLastFragment);  
            }  
            if (newInfo == null) {  
                // 如果newInfo为空，则创建一个并添加到界面上  
                switch (tag) {  
                    case TAB1:  
                        newInfo = new Fragment1();  
                        break;  
                    case TAB2:  
                        newInfo = new Fragment2();  
                        break;  
                    case TAB3:  
                        newInfo = new Fragment3();  
                        break;  
                }  
                mFragments.put(tag, newInfo);  
                transaction.add(R.id.framelayout, newInfo,  
                        String.valueOf(tag));  
            } else {  
                // 如果newInfo不为空，则直接将它显示出来 ，对应的这里可以用 transaction.attach(newInfo) 重建view视图，附加到UI上并显示，会重新执行onActivityView()  
                transaction.show(newInfo);  
    //          transaction.attach(newInfo);  
            }  
            mLastFragment = newInfo;  
            //提交事务  
            transaction.commit();  
        }  
        @Override  
        public void onClick(View v) {  
            int tag = (int) v.getTag();  
            setFragmentIndicator(tag);  
        }  
    }  

各个TabFragment、这里我用了3个、基本上都一样：

[html] view plain copy

    package com.test.tab;  
      
    import android.content.Context;  
    import android.os.Bundle;  
    import android.support.v4.app.Fragment;  
    import android.util.Log;  
    import android.view.LayoutInflater;  
    import android.view.View;  
    import android.view.ViewGroup;  
      
    /**  
     * Created by fuweiwei on 2015/12/19.  
     */  
    public class Fragment1 extends Fragment {  
        @Override  
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
            Log.d("test","---------onCreateView");  
            return inflater.inflate(R.layout.fragment1, container, false);  
        }  
      
        @Override  
        public void onCreate(Bundle savedInstanceState) {  
            Log.d("test","---------onCreate");  
            super.onCreate(savedInstanceState);  
        }  
      
        @Override  
        public void onActivityCreated(Bundle savedInstanceState) {  
            Log.d("test","---------onActivityCreated");  
            super.onActivityCreated(savedInstanceState);  
        }  
      
        @Override  
        public void onAttach(Context context) {  
            Log.d("test","---------onAttach");  
            super.onAttach(context);  
        }  
      
        @Override  
        public void onStart() {  
            Log.d("test","---------onStart");  
            super.onStart();  
        }  
      
        @Override  
        public void onDestroyView() {  
            Log.d("test","---------onDestroyView");  
            super.onDestroyView();  
        }  
      
        @Override  
        public void onStop() {  
            Log.d("test","---------onStop");  
            super.onStop();  
        }  
      
        @Override  
        public void onPause() {  
            Log.d("test","---------onPause");  
            super.onPause();  
        }  
      
        @Override  
        public void onDestroy() {  
            Log.d("test","---------onDestroy");  
            super.onDestroy();  
        }  
      
        @Override  
        public void onDetach() {  
            Log.d("test","---------onDetach");  
            super.onDetach();  
        }  
      
        @Override  
        public void onResume() {  
            Log.d("test","---------onResume");  
            super.onResume();  
        }  
    }  


这里我实现了Fragment整个生命周期的方法、不清楚Fragment生命周期的筒子可以在使用中看下日志，瞬间就应该理解了Fragment的生命周期了。使用这种方式独立性很高、fragment可以使用到任何地方，但是这种不支持作左右滑动。

2、使用ViewPager+Fragment实现

主要是使用Fragment当做ViewPage的Item，就可以实现左右滑动的Tab了

代码：

[html] view plain copy

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

这里使用的Fragment跟第一种一样、代码很精简、可以支持左右滑动了。又有问题了，当Tab标签很多很多时、因为头部的标签是不支持滑动的、所有我们想看后面的Tab只有一项一项的滑了、不是很方便，要是能直接拖到头部的选项就好了。好、接下来我们要使用第三种方式了。

3、使用Google在2015 IO大会带来的Material Design设计规范的TabLayout实现

3Google在2015 IO大会带来的Material Design设计规范，是为了统一android杂乱不堪的设计风格，里面有很多Google跟我们封装好的类，其中TabLayout就是用来统一Tab类型页面的，使用Material Design的时候我们要导入com.android.support:design:23.1.1包，这里23.1.1没关系、使用你Android Studio上的有版本就行。

代码：

[html] view plain copy

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


布局代码：
[html] view plain copy

    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
        xmlns:app="http://schemas.android.com/apk/res-auto"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical"  
        >  
        <android.support.design.widget.TabLayout  
            android:id="@+id/tablayout"  
            android:layout_width="match_parent"  
            app:tabIndicatorColor="@color/colorPrimary"  
            app:tabSelectedTextColor="@color/colorPrimary"  
            app:tabTextColor="@android:color/black"  
            android:layout_height="40dp">  
      
        </android.support.design.widget.TabLayout>  
        <View  
            android:layout_width="match_parent"  
            android:background="@color/colorPrimary"  
            android:layout_height="1.0dp"></View>  
        <android.support.v4.view.ViewPager  
            android:id="@+id/viewpager"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent">  
      
        </android.support.v4.view.ViewPager>  
    </LinearLayout>  

是不是很简单，因为Google已经帮我们封装了，省去了第一种和第二种方式的一些代码，我们直接调用。使用TabLayout时我们只需要设置Tab模式为TabLayout.MODE_SCOLLABLE，我们就可以滑动头部的tab到任意Tab页面去了。

好了、以上3种方式基本上可以满足我们大部分Tab类型页面的需求了，当然还有很多实现的方式，这里只是把我知道的分享出来顺便自己总结记录，方便也会查看。下面有项目的代码、代码环境是Android studio的。
