package com.example.javakotlinders10instagramklonparsile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PostsListAdapter extends ArrayAdapter<String> {
    private final ArrayList<String> username;
    private final ArrayList<Bitmap> images;
    private final ArrayList<String> comment;
    private final Activity activity;

    public PostsListAdapter(ArrayList<String> username, ArrayList<Bitmap> images, ArrayList<String> comment, Activity activity) {
        super(activity, R.layout.listview_layout, username);

        this.activity = activity;
        this.comment = comment;
        this.images = images;
        this.username = username;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.listview_layout, null, true);

        TextView kAdiListText = view.findViewById(R.id.kAdiListText);
        TextView commentListText = view.findViewById(R.id.commentListText);
        ImageView listImageView = view.findViewById(R.id.listImageView);

        kAdiListText.setText(username.get(position));
        commentListText.setText(comment.get(position));
        listImageView.setImageBitmap(images.get(position));

        return view;
    }
}
