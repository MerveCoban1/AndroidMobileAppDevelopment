package com.example.firebaseegitim2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    public List<Messages> list;
    public Context context;

    public MessagesAdapter(List<Messages> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_layout,parent,false);

        return new MessagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {
        //şimdi set işlemlerini yapacağız
        holder.date.setText(list.get(position).date.toString());
        holder.icerik.setText(list.get(position).icerik.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //recyclerview a ait layoutun viewlarının tanımlanması işlmleri yapılıyor.
        TextView date,icerik;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date);
            icerik=(TextView)itemView.findViewById(R.id.icerik);
        }
    }
}
