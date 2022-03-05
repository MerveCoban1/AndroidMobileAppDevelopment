package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retrofitkutuphanesi.R;

import java.util.List;

import Models.Bilgiler;
import RestApi.BaseManager;

public class AdapterSinifi extends BaseAdapter {
    List<Bilgiler> list;
    Context context;

    public AdapterSinifi(List<Bilgiler> list, Context context) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.listviewlayout,parent,false);

        TextView userId=(TextView) convertView.findViewById(R.id.userId);
        TextView id=(TextView)convertView.findViewById(R.id.id);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView body=(TextView)convertView.findViewById(R.id.body);

        userId.setText(""+list.get(position).getUserId());
        id.setText(""+list.get(position).getId());
        title.setText(""+list.get(position).getTitle());
        body.setText(""+list.get(position).getBody());

        return convertView;
    }
}
