package com.example.otogaleri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    SharedPreferences.Editor editor;
    Button ilanVerButon,ilanlarimButon,tumIlanlar,favoriButton,mesajlasmaButonu;
    ViewFlipper v_flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giriskayit", 0);
        navHeaderText = sharedPreferences.getString("uye_mail", null);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.cikisYap)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View headerView = navigationView.getHeaderView(0);
        navHeaderTextView = (TextView) headerView.findViewById(R.id.navHeaderText);
        navHeaderTextView.setText(navHeaderText);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.cikisYap){
                    editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                }
                return true;
            }
        });

        tanimla();
    }
    public void tanimla(){
        ilanlarimButon=(Button)findViewById(R.id.ilanlarimButon);
        mesajlasmaButonu=(Button)findViewById(R.id.mesajlasmaButonu);
        favoriButton=(Button)findViewById(R.id.favoriButton);
        ilanVerButon=(Button)findViewById(R.id.ilanVerButon);
        tumIlanlar=(Button)findViewById(R.id.tumIlanlar);
        v_flipper=(ViewFlipper)findViewById(R.id.v_flipper);

        int images[]={R.drawable.araba1,R.drawable.araba2,R.drawable.araba3,R.drawable.araba4};
        /*for(int i=0;i<images.length;i++){
            flipperImages(images[i]);
        }*/ //ya da bunun yerine foreach döngüsü kullanabiliriz.
        for (int image: images){
            flipperImages(image);
        }

        ilanVerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,IlanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        ilanlarimButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(MainActivity.this,Ilanlarim.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        tumIlanlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(MainActivity.this,TumIlanlar.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        favoriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(MainActivity.this,Sicaklik.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        mesajlasmaButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(MainActivity.this,ChatActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

}
