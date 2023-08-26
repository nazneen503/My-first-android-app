package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class missing_recycler_adapter2 extends RecyclerView.Adapter<missing_recycler_adapter2.myholder> {
    Context context;
    ArrayList<missing_recycler_structure> arrmissingDes;
    public missing_recycler_adapter2(Context context,ArrayList<missing_recycler_structure> arrmissingDes){
        this.context=context;
        this.arrmissingDes=arrmissingDes;
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_missing_nodesc,viewGroup,false);
        myholder myholder=new myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, int i) {
        myholder.text.setText(arrmissingDes.get(i).text);
    }

    @Override
    public int getItemCount() {
        return arrmissingDes.size();
    }

    public class myholder extends RecyclerView.ViewHolder{
        TextView text;
        public myholder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
        }
    }
}
