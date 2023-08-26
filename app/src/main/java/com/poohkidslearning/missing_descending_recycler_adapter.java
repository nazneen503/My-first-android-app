package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class missing_descending_recycler_adapter extends RecyclerView.Adapter<missing_descending_recycler_adapter.myholder> {
  Context context;
    ArrayList<String> arrHint;
    ArrayList<Integer> arrBlanks=new ArrayList<>();
    ArrayList<String> arrChar=new ArrayList<>();
    public missing_descending_recycler_adapter(Context context, ArrayList<String> arrHint, ArrayList<String> arrChar, ArrayList<Integer> arrBlanks) {
        this.context=context;
        this.arrHint=arrHint;
        this.arrBlanks = arrBlanks;
        this.arrChar = arrChar;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.missing_desc_recycler,viewGroup,false);
       myholder myholder=new myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, final int i) {
        myholder.text1.setText(arrHint.get(i));
        myholder.llHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((missing_desc_order)context).AddLetter(arrHint.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrHint.size();
    }

    public class myholder extends RecyclerView.ViewHolder {
        TextView text1;
        LinearLayout llHint;
        public myholder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.text1);
            llHint=itemView.findViewById(R.id.llHint);
        }
    }

}
