package com.example.javakotlinders8instaclonefirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userBaslikList;
    private ArrayList<String> userYorumList;
    private ArrayList<String> userResimList;

    public FeedRecyclerAdapter(ArrayList<String> userEmailList, ArrayList<String> userBaslikList, ArrayList<String> userYorumList, ArrayList<String> userResimList) {
        this.userEmailList = userEmailList;
        this.userBaslikList = userBaslikList;
        this.userYorumList = userYorumList;
        this.userResimList = userResimList;
    }

    @NonNull
    @Override//holder oluşturulunca ne yapacağımızı yazıyoruz
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row_layout,parent,false);


        return new PostHolder(view);
    }

    @Override//bi holdera bağlanınca ne yapacağımızı yazıyoruz.
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.userEmailText.setText(userEmailList.get(position));
        holder.commentText.setText(userYorumList.get(position));
        holder.baslikText.setText(userBaslikList.get(position));
        Picasso.get().load(userResimList.get(position)).into(holder.imageView);

    }

    @Override//recyclerView'ımızda kaç tane row olacak onu yazıyoruz
    public int getItemCount() {
        return userEmailList.size();
    }

    //viewları tanımlıyoruz
    class PostHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView userEmailText,commentText,baslikText;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.rViewResimImageView);
            userEmailText=itemView.findViewById(R.id.rViewKullaniciAdiTextView);
            commentText=itemView.findViewById(R.id.rViewYorumTextView);
            baslikText=itemView.findViewById(R.id.rViewBaslikTextView);



        }
    }
}
