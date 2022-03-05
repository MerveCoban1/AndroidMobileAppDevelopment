package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofitornek3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models.YeniBilgi;

public class AdapterListIcin extends BaseAdapter {
    List<YeniBilgi> list;
    Context context;

    public AdapterListIcin(List<YeniBilgi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.layoutlistview,parent,false);
        TextView albumId=(TextView)convertView.findViewById(R.id.albumId);
        TextView id=(TextView)convertView.findViewById(R.id.id);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        ImageView thumbnailUrl=(ImageView)convertView.findViewById(R.id.thumbnailUrl);

        albumId.setText(""+list.get(position).getAlbumId());
        id.setText(""+list.get(position).getId());
        title.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getThumbnailUrl()).into(thumbnailUrl);
        return convertView;
    }
}
