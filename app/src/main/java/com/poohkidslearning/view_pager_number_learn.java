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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class view_pager_number_learn extends PagerAdapter {
    Context context;
    ArrayList<suitcaseNumberLearning> numberArrayList=new ArrayList<>();
    view_pager_number_learn(Context context,ArrayList<suitcaseNumberLearning> numberArrayList){
        this.context=context;
        this.numberArrayList=numberArrayList;
    }
    @Override
    public int getCount() {

        return numberArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v= LayoutInflater.from(context).inflate(R.layout.customiznumberlearning,null,false);
        ImageView imgv=v.findViewById(R.id.imgv);
        TextView count_text=v.findViewById(R.id.count_text);
        TextView image_name_text=v.findViewById(R.id.image_name_text);
        TextView count_number=v.findViewById(R.id.count_number);

        byte[]pic=numberArrayList.get(position).imgv;
        Bitmap image= BitmapFactory.decodeByteArray(pic,0,pic.length);
        imgv.setImageBitmap(image);
        count_text.setText(numberArrayList.get(position).count_text);
        count_number.setText(numberArrayList.get(position).count_number);
        image_name_text.setText(numberArrayList.get(position).image_name_text);


        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
