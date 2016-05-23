package com.demo.tom.mblocker;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by tom on 16/5/23.
 */
public class NewsFragmentItem extends Fragment {

    private final String TAG = NewsFragmentItem.class.getSimpleName();

    private String mContent = "";

    private static Bitmap[] images;

    private static int index;

    ImageView imageItem;

    public NewsFragmentItem newInstance(String content) {

        mContent = content;

        NewsFragmentItem fragment = new NewsFragmentItem();

        return fragment;
    }

    public static void setIndex(int index) {
        NewsFragmentItem.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.news_item,container,false);
        imageItem = (ImageView) rootView.findViewById(R.id.news_image);
        imageItem.setImageBitmap(images[index]);

        return rootView;
    }
}
