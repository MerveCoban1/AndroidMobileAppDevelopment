package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.otogaleri.R;
import java.util.List;

import Models_ılerledikce_verileri_tutuyoruz.IlanlarimPojoSinifi;

public class IlanlarimAdapter extends BaseAdapter {
    List<IlanlarimPojoSinifi> list;
    Context context;
    Activity activity;
    String ilan_id;

    public IlanlarimAdapter(List<IlanlarimPojoSinifi> list,Context context,Activity activity) {
        this.context = context;
        this.list = list;
        this.activity=activity;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.ilanlarim_adapter_listview,parent,false);
        ImageView ilanlarimIlanResim;
        TextView ilanlarimIlanBaslik,ilanlarimIlanUcret;
        ilanlarimIlanResim=(ImageView) convertView.findViewById(R.id.ilanlarimIlanResim);
        ilanlarimIlanBaslik=(TextView) convertView.findViewById(R.id.ilanlarimIlanBaslik);
        ilanlarimIlanUcret=(TextView)convertView.findViewById(R.id.ilanlarimIlanUcret);

        ilanlarimIlanBaslik.setText(list.get(position).getBaslik());
        ilanlarimIlanUcret.setText(list.get(position).getFiyat());
        ilan_id=list.get(position).getIlan_id();

        //resim için picasso kütüphanesini kullanacağız
        //Picasso.with(context).load(list.get(position).getResim()).into(ilanlarimIlanResim);
//burda resim cekmeyi arastir iyice.

        return convertView;
    }
}
