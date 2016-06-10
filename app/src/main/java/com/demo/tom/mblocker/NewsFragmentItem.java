package com.demo.tom.mblocker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by tom on 16/6/9.
 */
public class NewsFragmentItem extends Fragment {

    private final String TAG = NewsFragmentItem.class.getSimpleName();

    private int position = 0;

    private ImageView imageItem;

    public NewsFragmentItem(){
        Log.e(TAG,"新的碎片被构造");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = getArguments().getInt("position");

        View rootView = inflater.inflate(R.layout.news_item,container,false);
        imageItem = (ImageView) rootView.findViewById(R.id.news_image);
        imageItem.setImageBitmap(NewsFragment.newsItems[position]);

        return rootView;
    }


}
