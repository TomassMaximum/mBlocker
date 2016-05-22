package com.demo.tom.mblocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.PageIndicator;

/**
 * Created by tom on 16/5/22.
 */
public class NewsFragment extends Fragment {

    MyFragmentAdapter mAdapter;
    LoopViewPager mPager;
    PageIndicator mIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news,container,false);

        mAdapter = new MyFragmentAdapter(getChildFragmentManager());

        mPager = (LoopViewPager) rootView.findViewById(R.id.viewpager_news_list);
        mPager.setAdapter(mAdapter);

        mIndicator = (PageIndicator) rootView.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        return rootView;
    }


}
