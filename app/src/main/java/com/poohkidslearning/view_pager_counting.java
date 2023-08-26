package com.poohkidslearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class view_pager_counting extends PagerAdapter {
    Context context;
    ArrayList<suitcase_counting> countingArrayList=new ArrayList<>();
    view_pager_counting(Context context,ArrayList<suitcase_counting> countingArrayList){
        this.context=context;
        this.countingArrayList=countingArrayList;
    }
    @Override
    public int getCount() {

        return countingArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
    }
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v= LayoutInflater.from(context).inflate(R.layout.customized_counting,null,false);
        ImageView image=v.findViewById(R.id.image);
        TextView opt1=v.findViewById(R.id.opt1);
        TextView opt2=v.findViewById(R.id.opt2);
        TextView opt3=v.findViewById(R.id.opt3);

        byte[] pic=countingArrayList.get(position).image;
        Bitmap image1= BitmapFactory.decodeByteArray(pic,0,pic.length);
        image.setImageBitmap(image1);
        opt1.setText(""+countingArrayList.get(position).opt1);
        opt2.setText(""+countingArrayList.get(position).opt2);
        opt3.setText(""+countingArrayList.get(position).opt3);


        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
