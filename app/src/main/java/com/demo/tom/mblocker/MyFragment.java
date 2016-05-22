package com.demo.tom.mblocker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tom on 16/5/22.
 */
public class MyFragment extends Fragment {

    public static MyFragment newInstance(String content) {
        MyFragment fragment = new MyFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.news_item,container,false);
    }
}
