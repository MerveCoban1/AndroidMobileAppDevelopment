package com.example.javakotlinders4artbooktasarim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    EditText artName, painterName, year;
    Button kaydet;
    Bitmap selectedImage;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        action();
    }

    public void tanimla() {

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        imageView = findViewById(R.id.imageView);
        artName = findViewById(R.id.artName);
        painterName = findViewById(R.id.painterName);
        year = findViewById(R.id.year);
        kaydet = findViewById(R.id.kaydet);
        database = this.openOrCreateDatabase("Arts", MODE_PRIVATE, null);

        if (info.matches("new")) {
            artName.setText("");
            painterName.setText("");
            year.setText("");
            kaydet.setVisibility(View.VISIBLE);
            Bitmap selectImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.dene);
            imageView.setImageBitmap(selectImage);

        } else {
            int artId = intent.getIntExtra("artId", 1);
            kaydet.setVisibility(View.INVISIBLE);
            verileriCek(artId);
        }
    }

    public void action() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //e??er izin verilmediyse izin alaca????z.Galerisine eri??ebilmek i??in
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGallery, 2);
                }
            }
        });
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String art_name = artName.getText().toString();
                String painter_name = painterName.getText().toString();
                String yea_r = year.getText().toString();

                Bitmap smallImage = makeSmallerImage(selectedImage, 300);

                //compress veriyi ??evirme i??lemi yaparken kullanaca????m??z ??zellikleri belirtmemize yar??yor.
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] byteArray = outputStream.toByteArray();
                //resmimizi ??uan ??evirdik.
                //SQLite da resimlerin kalitesinin ??ok d??????k olmas?? laz??m boyutlar??n?? kendimiz tekrar k??????ltece??iz.
                //SQLite ????kebilir y??ksek resimlerde
                veritabaninaKaydet(art_name, painter_name, yea_r, byteArray);
            }
        });
    }

    //e??er izin verildiyse elseden direk galeriye gidilecek.Ama izin verilmediyse kullan??c??dan izin al??nacak
    //biz de izin verildiyse e??er hemen tekrar galeriye gitsin istiyoruz.
    //bu metot da izin verildiyse olacaklar?? i??eriyor.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    //bu versiyon yeni geldi ama eski sdkl?? telefonlarda ??al????m??yor.
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                    imageView.setImageBitmap(selectedImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void veritabaninaKaydet(String art_name, String painter_name, String yea_r, byte[] byteArray) {
        try {
            //byte dizilerini sqlite da blob olarak kaydedebiliyoruz.
            database.execSQL("CREATE TABLE IF NOT EXISTS arts(id INTEGER PRIMARY KEY,artname VARCHAR,paintername VARCHAR,year VARCHAR,image BLOB)");
            // database.execSQL("INSERT INTO arts (artname,paintername,year,image) VALUES(?,?,?,?)");
            //normalde b??yle yap??l??yodu ama biz farkl?? bi??ey deniycez.
            String sqlString = "INSERT INTO arts (artname,paintername,year,image) VALUES(?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1, art_name);//burda dizi gibi d??????nme indexler 1den ba??l??yor.
            sqLiteStatement.bindString(2, painter_name);
            sqLiteStatement.bindString(3, yea_r);
            sqLiteStatement.bindBlob(4, byteArray);
            sqLiteStatement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//di??er a????k olan t??m aktiviteleri kapat??yoruz.
        startActivity(i);

        //finish();//aktiviteyi bitiriyor.ama di??er aktiviteye ge??ince o aktivitenin oncreate
        //metodu ??al????m??yor onResume metodu ??al??????yor.
    }

    //bu metot ile resmin mehabyte'??n?? k??????ltece??iz.
    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void verileriCek(int artId) {
        try {
            //sqlite statement yapamay??z.Sorgu yap??yoruz.executesql komutunu kullanm??yoruz.
            Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id=?", new String[]{String.valueOf(artId)});
            int artNameIndex = cursor.getColumnIndex("artname");
            int painterNameIndex = cursor.getColumnIndex("paintername");
            int yearIndex = cursor.getColumnIndex("year");
            int imageIndex = cursor.getColumnIndex("image");

            while (cursor.moveToNext()) {
                artName.setText(cursor.getString(artNameIndex));
                painterName.setText(cursor.getString(painterNameIndex));
                year.setText(cursor.getString(yearIndex));

                byte[] bytes = cursor.getBlob(imageIndex);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
