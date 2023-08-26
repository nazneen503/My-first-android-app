package com.poohkidslearning;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class quiz_recycler_adapter extends RecyclerView.Adapter<quiz_recycler_adapter.ViewHolder>{
    Context context;
    ArrayList<dash_quiz_recycler_structure> arrsuitcase=new ArrayList<>();
    int prevPos = 100;
    ViewHolder prevHolder = null;

    public quiz_recycler_adapter(Context context,ArrayList<dash_quiz_recycler_structure>arrsuitcase){
    this.context=context;
    this.arrsuitcase=arrsuitcase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_quiz_match_no,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
    viewHolder.relative_match.setBackgroundResource(arrsuitcase.get(i).linear_match);
   viewHolder.match_img.setImageResource(R.drawable.logo);

        byte[]pic=arrsuitcase.get(i).match_img;
        Bitmap image= BitmapFactory.decodeByteArray(pic,0,pic.length);
        viewHolder.match_img1.setImageBitmap(image);
        //viewHolder.match_img1.setImageResource(arrsuitcase.get(i).match_img);
        viewHolder.relative_match.setTag(arrsuitcase.get(i).tag);


        viewHolder.relative_match.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View view) {


            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip);
            set.setTarget(viewHolder.relative_match);
            set.start();

            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    viewHolder.match_img.setVisibility(View.GONE);
                    viewHolder.match_img1.setVisibility(View.VISIBLE);

                    if(prevHolder!=null) {
                        if (viewHolder.relative_match.getTag() == prevHolder.relative_match.getTag()) {
                            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show();
                            prevHolder = null;
                        } else {
                            AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip);
                            set1.setTarget(prevHolder.relative_match);
                            set1.start();

                            set1.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    prevHolder.match_img1.setVisibility(View.GONE);
                                    prevHolder.match_img.setVisibility(View.VISIBLE);
                                    prevHolder = viewHolder;
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                        }
                    } else {
                        prevHolder = viewHolder;
                    }


                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });


        }
    });



    }

    @Override
    public int getItemCount() {
        return arrsuitcase.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout relative_match;
    ImageView match_img,match_img1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relative_match=itemView.findViewById(R.id.relative_match);
            match_img=itemView.findViewById(R.id.match_img);
            match_img1=itemView.findViewById(R.id.match_imgReverse);

        }
    }
}