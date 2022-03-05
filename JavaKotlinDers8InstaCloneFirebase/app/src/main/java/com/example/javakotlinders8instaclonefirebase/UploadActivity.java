package com.example.javakotlinders8instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
/*
 * büyük verileri kaydederken mesela resim video gibi bunları veritabanına direk kaydetmiyoruz.
 * Storage da saklıyoruz. Ordan veritabanına url şeklinde kaydediyoruz.
 *FireStore:Koleksiyon ve döküman dediğimiz farklı yapılardan  oluşuyor.
 * veriler veritabanından 2 şekilde alınabiliyor.
 * 1)bir kez hepsini alma
 * 2)veriler güncellendikçe verileri sürekli alma.
 * */


public class UploadActivity extends AppCompatActivity {
    EditText baslikEditText, yorumEditText;
    ImageView selectedImage;
    Button ekleButton;

    Bitmap bitmap;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Uri imageData;


    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        tanimla();
        action();

    }

    private void action() {
        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Galerimize gideceğiz.Bunun içim manifestte readExternalStorage iznini almamız gerekiyor.
                //şimdi izin var mı yok mu onu kontrol edeceğiz.
                //APİ 23 ve öncesinde uyumlu olabilmesi için ContextCompat kullanıyoruz.Çünkü 23 öncesinde kullanıcının bir iznini istemiyoruz
                //manifeste eklememiz yeterli oluyor.
                if (ContextCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //izin verilmediyse. izin istiyoruz.
                    ActivityCompat.requestPermissions(UploadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    //o 1 yerine istediğimiz sayıyı verebiliriz. Sonradan kontrol etmek için yazıyoruz onu sadece.
                } else {
                    //galeriyi açtık
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGallery, 2);
                }
                //verdiğimiz requestCodelara göre sonuç metotlarını yazalım

            }
        });

        ekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageData != null) {
                    //her kaydettiğim resmin farklı isimle kaydeolmasını istediğim için Universal Unique Id kullanacağız.
                    //javanın içinde UUID diye bir sınıf var.
                    UUID uuid = UUID.randomUUID(); //bana rastgele uydurma bir id çıkar demek ouyor.
                    final String imageName = "images/" + uuid + ".jpg";

                    storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //şuan resmimizi storage ' a yükledik şimdi de resmin ordaki urlsini veritabanımıza kaydedeceğiz.
                            StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //şuan resmimizin url'ini aldık
                                    String downloadUrl = uri.toString();
                                    veritabaninaKaydet(downloadUrl);
                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Resim Yüklenemedi", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Resim Yok", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //izin verildi demek oluyor
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery, 2);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //galeriden fotoğraf seçildikten sonra nolcak onu yazıcaz
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageData = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    selectedImage.setImageBitmap(bitmap);
                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                    selectedImage.setImageBitmap(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void tanimla() {
        baslikEditText = findViewById(R.id.baslikEditText);
        yorumEditText = findViewById(R.id.yorumEditText);
        selectedImage = findViewById(R.id.selectedImage);
        ekleButton = findViewById(R.id.ekleButton);

        firebaseStorage = FirebaseStorage.getInstance();
        //kullanırken nereye ne kaydedeceğimizi belirtmemiz gerekiyor. Bunun için referans kullanıyoruz.
        storageReference = firebaseStorage.getReference();

        firebaseFirestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    public void veritabaninaKaydet(String downloadUrl) {
        String userEmail = user.getEmail().toString();
        String baslik = baslikEditText.getText().toString();
        String yorum = yorumEditText.getText().toString();
        //şimdi bu bilgileri veritabanına kaydedeceğim
        //keyimiz hep string olacak ama valuemiz stringte olabilir intte olabilir o yüzden object dedik.
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("useremail", userEmail);
        hashMap.put("baslik", baslik);
        hashMap.put("yorum", yorum);
        hashMap.put("resim", downloadUrl);
        //bu güncel zamanı alıp bize veriyor.
        hashMap.put("tarih", FieldValue.serverTimestamp());

        firebaseFirestore.collection("Posts").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent = new Intent(UploadActivity.this, FeedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//açık olan tüm aktivitileri kapat demek oluyor bu.
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Veritabanina veri eklenemedi", Toast.LENGTH_LONG).show();
            }
        });

    }
}
