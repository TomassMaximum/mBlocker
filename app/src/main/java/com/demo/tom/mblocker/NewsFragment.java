package com.demo.tom.mblocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by tom on 16/5/22.
 */
public class NewsFragment extends Fragment {

    MyFragmentAdapter mAdapter;
    LoopViewPager mPager;
    PageIndicator mIndicator;

    Timer timer;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news,container,false);

        mAdapter = new MyFragmentAdapter(getChildFragmentManager());

        mPager = (LoopViewPager) rootView.findViewById(R.id.viewpager_news_list);
        mPager.setAdapter(mAdapter);

        mIndicator = (PageIndicator) rootView.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        pageSwitcher(3);

        return rootView;
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    // this is an inner class...
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 5) { // In my case the number of pages are 5
                        page = 0;
                    } else {
                        mPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
