package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class view_pager_match_real_images extends PagerAdapter {
    Context context;
    ArrayList<suitcase_match_realimages> matchRealimagesArrayList=new ArrayList<>();
    view_pager_match_real_images(Context context,ArrayList<suitcase_match_realimages>matchRealimagesArrayList){
        this.context=context;
        this.matchRealimagesArrayList=matchRealimagesArrayList;

    }
    @Override
    public int getCount() {
        return matchRealimagesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v= LayoutInflater.from(context).inflate(R.layout.customized_match_real_image,null,false);
        /*TextView pattern1=v.findViewById(R.id.pattern1);
        TextView pattern2=v.findViewById(R.id.pattern2);
        TextView pattern3=v.findViewById(R.id.pattern3);
        pattern1.setText(matchRealimagesArrayList.get(position).pattern1);
        pattern2.setText(matchRealimagesArrayList.get(position).pattern2);
        pattern3.setText(matchRealimagesArrayList.get(position).pattern3);*/
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
