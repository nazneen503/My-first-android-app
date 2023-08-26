package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class recycler_table_adapter extends RecyclerView.Adapter<recycler_table_adapter.myholder> {

    Context context;
    ArrayList<table_recycler_structure> arrtable=new ArrayList<>();



    public recycler_table_adapter(Context context,ArrayList<table_recycler_structure> arrtable){
        this.context=context;
        this.arrtable=arrtable;
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view=LayoutInflater.from(context).inflate(R.layout.recycler_table,viewGroup,false);
       myholder myholder=new myholder(view);
       return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, int i) {
    myholder.num1.setText(arrtable.get(i).num1);

    }

    @Override
    public int getItemCount() {

        return arrtable.size();
    }

    public  class myholder extends RecyclerView.ViewHolder {
        TextView num1;
        public myholder(@NonNull View itemView) {
            super(itemView);
            num1=itemView.findViewById(R.id.num1);

        }
    }


}
