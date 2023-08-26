package com.poohkidslearning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class missing_desc_recycler_adapter extends RecyclerView.Adapter<missing_desc_recycler_adapter.myholder> {
    Context context;
    ArrayList<Integer> arrBlanks=new ArrayList<>();
    ArrayList<String> arrchar=new ArrayList<>();

    public missing_desc_recycler_adapter(Context context, ArrayList<String> arrchar, ArrayList<Integer> arrBlanks){
        this.context=context;
        this.arrchar=arrchar;
        this.arrBlanks=arrBlanks;
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_missing_desc,viewGroup,false);
        myholder myholder=new myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, int i) {
        myholder.text.setText(arrchar.get(i));
        for (int j = 0; j < arrBlanks.size(); j++) {
            if (arrBlanks.get(j) == i) {
                myholder.text.setText(" ");

            }
        }

    }

    @Override
    public int getItemCount() {
        return arrchar.size();
    }

    public class myholder extends RecyclerView.ViewHolder{
    TextView text;
    public myholder(@NonNull View itemView) {
        super(itemView);
        text=itemView.findViewById(R.id.text);
    }
}
}
