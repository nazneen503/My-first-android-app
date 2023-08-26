package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class missing_recycler_adapter extends RecyclerView.Adapter<missing_recycler_adapter.myholder> {
    Context context;
    ArrayList<missing_recycler_structure> arrmissing;
    public  missing_recycler_adapter( Context context,ArrayList<missing_recycler_structure> arrmissing){
        this.context=context;
        this.arrmissing=arrmissing;

    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_missing_no,viewGroup,false);
        myholder myholder=new myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, int i) {
            myholder.text.setText(arrmissing.get(i).text);

    }

    @Override
    public int getItemCount() {
        return arrmissing.size();

    }

    public class myholder extends RecyclerView.ViewHolder {
        TextView text;
        public myholder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);

        }
    }
}
