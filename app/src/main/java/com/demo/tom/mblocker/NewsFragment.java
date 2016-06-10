package com.demo.tom.mblocker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.PageIndicator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tom on 16/5/22.
 */
public class NewsFragment extends Fragment {

    private static final String TAG = NewsFragment.class.getSimpleName();

    MyFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    final Handler delayHandler = new Handler();

    public static int[] newsItemsId = {R.drawable.news_codeybot,R.drawable.news_mbot,R.drawable.news_ultimate,R.drawable.news_printer};

    public static Bitmap[] newsItems = new Bitmap[4];

    Timer timer;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getBitmapFromResource();

        View rootView = inflater.inflate(R.layout.fragment_news,container,false);

        mAdapter = new MyFragmentAdapter(getChildFragmentManager());

        mPager = (ViewPager) rootView.findViewById(R.id.viewpager_news_list);
        mPager.setAdapter(mAdapter);

        mIndicator = (PageIndicator) rootView.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageSwitcher(3);
            }
        },3000);

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

                    if (page > 3) { // In my case the number of pages are 5
                        page = 0;
                        mPager.setCurrentItem(page);
                        page++;
                    } else {
                        mPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

    @Override
    public void onDetach() {
        timer.cancel();
        super.onDetach();
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e(TAG,"当前位置为:" + position);
            NewsFragmentItem newsFragmentItem = new NewsFragmentItem();
            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            newsFragmentItem.setArguments(bundle);
            return newsFragmentItem;
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

    public void getBitmapFromResource(){
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < 4;i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            Bitmap image = BitmapFactory.decodeResource(getResources(),newsItemsId[i],options);

            newsItems[i] = image;
        }
        long endTime = System.currentTimeMillis();
        Log.e(TAG,"获取图片资源共耗时:" + (endTime - startTime));
    }

}
