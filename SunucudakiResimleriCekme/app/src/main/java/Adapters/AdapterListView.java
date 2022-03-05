package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunucudakiresimlericekme.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models.urunler;

public class AdapterListView extends BaseAdapter {
    List<urunler> list;
    Context context;

    public AdapterListView(List<urunler> list, Context context) {
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
        TextView urunIsmi,urunFiyati,urunId;
        ImageView urunResmi;
        urunFiyati=(TextView) convertView.findViewById(R.id.urunFiyati);
        urunIsmi=(TextView) convertView.findViewById(R.id.urunIsmi);
        urunId=(TextView) convertView.findViewById(R.id.urunId);
        urunResmi=(ImageView)convertView.findViewById(R.id.urunResmi);

        urunIsmi.setText("urun adi:"+list.get(position).getUrunadi());
        urunFiyati.setText("urun fiyati:"+list.get(position).getUrunfiyati());
        urunId.setText("urun id:"+list.get(position).getId());

        Picasso.with(context).load("https://muratkoc93.xyz/resimler/"+list.get(position).getUrunresmi()).into(urunResmi);
        return convertView;
    }
}
