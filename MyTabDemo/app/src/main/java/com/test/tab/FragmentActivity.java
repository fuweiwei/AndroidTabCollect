package com.test.tab;

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
