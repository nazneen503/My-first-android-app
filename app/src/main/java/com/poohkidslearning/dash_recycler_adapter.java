package com.poohkidslearning;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class dash_recycler_adapter extends RecyclerView.Adapter<dash_recycler_adapter.myHolder> {
    Context context;
    ArrayList<dash_recycler_structure> arrsuitcase=new ArrayList<>();

    /* ImageView img1;*/

    public dash_recycler_adapter(Context context,ArrayList<dash_recycler_structure> arrsuitcase){
        this.context=context;
        this.arrsuitcase=arrsuitcase;
    }@NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_recycler,viewGroup,false);
        myHolder myHolder=new myHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, final int i) {
        final  int i1=myHolder.getAdapterPosition();
           myHolder.img_number_b1.setImageResource(arrsuitcase.get(i).img_number_b1);
           myHolder.img_numbers.setImageResource(arrsuitcase.get(i).img_numbers);
           myHolder.txt_match.setText(arrsuitcase.get(i).txt_match);
           myHolder.rel_number_box.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (i1==0){
                       Intent i= new Intent(context,dash_match_no.class);
                       context.startActivity(i);
                   }
                   else if (i1==1){
                       Intent i= new Intent(context,dash_counting.class);
                       context.startActivity(i);

                   }
                   else if (i1==2){
                       Intent i=new Intent(context,dash_missing_no.class);
                       context.startActivity(i);
                   }
                   else if (i1==3){
                       Intent i=new Intent(context,match_real_img.class);
                       context.startActivity(i);
                   }
               }
           });

    }


    @Override
    public int getItemCount() {
        return arrsuitcase.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
     RelativeLayout rel_number_box;
     ImageView img_number_b1,img_numbers;
     TextView txt_match;
        public myHolder(@NonNull View itemView)
        {
            super(itemView);
            rel_number_box=itemView.findViewById(R.id.rel_number_box);
            img_number_b1=itemView.findViewById(R.id.img_number_b1);
            img_numbers=itemView.findViewById(R.id.img_numbers);
            txt_match=itemView.findViewById(R.id.txt_match);
        }
    }
}
