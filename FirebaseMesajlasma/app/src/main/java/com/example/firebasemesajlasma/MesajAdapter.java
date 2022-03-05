package com.example.firebasemesajlasma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter {
    List<MesajModel> list;
    boolean state = false;
    static final int user = 5, other = 8;
    Context context;

    public MesajAdapter(List<MesajModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == user) {
            view = LayoutInflater.from(context).inflate(R.layout.user, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.other, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MesajModel m = list.get(position);
        switch (holder.getItemViewType()){
            case user:{
                ((ViewHolder) holder).setle(m);
            } case other:{
                ((ViewHolder) holder).setle(m);
            }
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mesajBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (state) {
                mesajBody = (TextView) itemView.findViewById(R.id.userText);
            } else {
                mesajBody = (TextView) itemView.findViewById(R.id.otherText);
            }
        }

        void setle(MesajModel msj) {
            mesajBody.setText(msj.getMesaj().toString());
            // mesajBody.setText(list.get(pos).getMesaj().toString()); //bu da aynı şey
            //parametrede int pos alıcan ama pos=position
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals("1")) {
            state = true;
            return user;
        } else {
            state = false;
            return other;
        }
    }
}
