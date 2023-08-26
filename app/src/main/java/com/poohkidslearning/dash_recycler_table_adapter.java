package com.poohkidslearning;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class dash_recycler_table_adapter extends RecyclerView.Adapter<dash_recycler_table_adapter.myholder> {
    Context context;
    ArrayList<structure_dash_recycler_table> arrtableno=new ArrayList<>();
   public dash_recycler_table_adapter(Context context,ArrayList<structure_dash_recycler_table> arrtableno){
    this.context=context;
    this.arrtableno=arrtableno;
}
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.dash_recycler_table,viewGroup,false);
        myholder myholder=new myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, final int i) {
        myholder.table_2.setBackgroundResource(arrtableno.get(i).table_2);
        myholder.texttableno.setText(arrtableno.get(i).texttableno);
        myholder.table_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int j=0;j<arrtableno.size();j++){


                if (i==j){
                    Intent i1= new Intent (context,dash_table_learn.class);
                    i1.putExtra("flag",""+i);
                    context.startActivity(i1);
                }
            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrtableno.size();
    }

    public class myholder extends RecyclerView.ViewHolder{
    LinearLayout table_2;
    TextView texttableno;
    public myholder(@NonNull View itemView) {
        super(itemView);
        table_2=itemView.findViewById(R.id.table_2);
        texttableno=itemView.findViewById(R.id.texttableno);
    }
}
}
