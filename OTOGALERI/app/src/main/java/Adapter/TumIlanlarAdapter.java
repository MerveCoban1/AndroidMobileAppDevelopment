package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otogaleri.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models_ılerledikce_verileri_tutuyoruz.TumIlanlarPojoSinifi;

public class TumIlanlarAdapter extends BaseAdapter {
    List<TumIlanlarPojoSinifi> list;
    Context context;
    Activity activity;

    public TumIlanlarAdapter(List<TumIlanlarPojoSinifi> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.tum_ilanlar_adapter_listview,parent,false);
        ImageView tumIlanİçinImageView;
        TextView aciklama,baslik,sehir,fiyat;
        tumIlanİçinImageView=(ImageView)convertView.findViewById(R.id.tumIlanİçinImageView);
        aciklama=(TextView)convertView.findViewById(R.id.aciklama);
        baslik=(TextView)convertView.findViewById(R.id.baslik);
        sehir=(TextView)convertView.findViewById(R.id.sehir);
        fiyat=(TextView)convertView.findViewById(R.id.fiyat);

        aciklama.setText(list.get(position).getAciklama());
        baslik.setText(list.get(position).getBaslik());
        sehir.setText(list.get(position).getSehir());
        fiyat.setText(list.get(position).getUcret());
        Picasso.with(context).load(list.get(position).getResim()).resize(100,100).into(tumIlanİçinImageView);
        //tumIlanİçinImageView.setImageResource(list.get(position).getResim());



        return convertView;
    }
}
