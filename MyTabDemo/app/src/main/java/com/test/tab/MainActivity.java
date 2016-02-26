package com.test.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private Button mBut1,mBut2,mBut3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private  void initView(){
        mBut1 = (Button) findViewById(R.id.but1);
        mBut2 = (Button) findViewById(R.id.but2);
        mBut3 = (Button) findViewById(R.id.but3);
        mBut1.setOnClickListener(this);
        mBut2.setOnClickListener(this);
        mBut3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        Intent intent = null;
        switch (id){
            case R.id.but1:
                intent = new Intent(MainActivity.this, com.test.tab.FragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.but2:
                intent = new Intent(MainActivity.this, com.test.tab.ViewPageFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.but3:
                intent = new Intent(MainActivity.this, com.test.tab.TabLayoutActivity.class);
                startActivity(intent);
                break;
        }

    }
}
