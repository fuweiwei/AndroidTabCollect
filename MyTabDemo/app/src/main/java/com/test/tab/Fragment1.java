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
