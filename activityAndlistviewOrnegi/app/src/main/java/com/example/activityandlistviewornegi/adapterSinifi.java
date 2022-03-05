package com.example.activityandlistviewornegi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class adapterSinifi extends BaseAdapter {
    List<modelSinifi> list;
    Context context;
    Activity activity;

    public adapterSinifi(List<modelSinifi> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        TextView isim =(TextView) convertView.findViewById(R.id.isim);
        TextView soyisim = (TextView) convertView.findViewById(R.id.soyisim);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.anaLayout);
        final String isimDeger = list.get(position).getIsim();
        final String soyisimDeger = list.get(position).getSoyisim();
        //final yazmazsam eğer inner classta kullanacağım için hata veriyor.
        isim.setText(isimDeger);
        soyisim.setText(soyisimDeger);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //diğer activity ye geçiş yapacağız
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("isim", isimDeger);
                intent.putExtra("soyisim", soyisimDeger);
                //bunu çalıştırabilmem için start activity demem gerekiyor  ama bu activity classı değil
                //o yüzden main classımızdan adapter sınıfı oluştururken activity de yollayacağız.
                activity.startActivity(intent);

            }
        });
        return convertView;
    }
}
