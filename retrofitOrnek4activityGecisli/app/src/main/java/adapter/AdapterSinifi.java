package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retrofitornek4activitygecisli.Main2Activity;
import com.example.retrofitornek4activitygecisli.R;

import java.util.List;

import Models.Bilgi;

public class AdapterSinifi extends BaseAdapter {
    List<Bilgi> list;
    Context context;
    Activity activity;

    public AdapterSinifi(List<Bilgi> list, Context context,Activity activity) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        TextView postId,id,name,email,body;
        LinearLayout layout=(LinearLayout) convertView.findViewById(R.id.layoutList);
        postId=(TextView)convertView.findViewById(R.id.postId);
        id=(TextView)convertView.findViewById(R.id.id);
        name=(TextView)convertView.findViewById(R.id.name);
        email=(TextView)convertView.findViewById(R.id.email);
        body=(TextView)convertView.findViewById(R.id.body);

        postId.setText(""+list.get(position).getPostId());
        id.setText(""+list.get(position).getId());
        name.setText(list.get(position).getName());
        email.setText(list.get(position).getEmail());
        body.setText(list.get(position).getBody());

        final String postIdg=""+list.get(position).getPostId();
        final String idg=""+list.get(position).getId();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //başka bir activity'ye gideceğiz bunu ınstentlerle yapıyoruz.
                Intent intent=new Intent(activity, Main2Activity.class);
                intent.putExtra("postId",postIdg);
                intent.putExtra("id",idg);
                activity.startActivity(intent);

            }
        });
        return convertView;
    }
}
