package com.example.kotlinders7sanatkitabimuygulamasi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.jar.Manifest

class Main2Activity : AppCompatActivity() {
    var selectedPicture: Uri? = null
    var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent=intent
        val info=intent.getStringExtra("info")
        if (info.equals("new")){
            artNametextView.setText("")
            painterNametextView.setText("")
            yeartextView.setText("")
            save.visibility=View.VISIBLE

            val selectedImageBackground=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.select_imagee)
            selectImageView.setImageBitmap(selectedImageBackground)
        }else{
            save.visibility=View.INVISIBLE
            val selectedId=intent.getIntExtra("id",0)
            //şimdi veritabanından bu idye ait verileri çekeceğiz
            try {
                val database = this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE, null)
                val cursor = database.rawQuery("SELECT * FROM arts WHERE id=?", arrayOf(selectedId.toString()))
                val artnameIx = cursor.getColumnIndex("artname")
                val artistnameIx = cursor.getColumnIndex("artistname")
                val yearIx = cursor.getColumnIndex("year")
                val imageIx = cursor.getColumnIndex("image")

                while (cursor.moveToNext()) {
                    artNametextView.setText(cursor.getString(artnameIx))
                    painterNametextView.setText(cursor.getString(artistnameIx))
                    yeartextView.setText(cursor.getString(yearIx))

                    //görseli bytearray olarak alıyoruz
                    val byteArray=cursor.getBlob(imageIx)
                    val bitmap=BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                    selectImageView.setImageBitmap(bitmap)
                }
                cursor.close()


            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun selectImage(view: View) {
        //önce izin almamız gerekiyor.Ammaaa her türlü izni önce manifest dosyasına yazmamız gerekiyor.
        //alacağımız izin: REAd_external_storage yani adamın hafızasını okumak
        izinVarMıYokMu()


    }

    fun save(view: View) {
        val artName = artNametextView.text.toString()
        val artisName = painterNametextView.text.toString()
        val year = yeartextView.text.toString()
        //görselleri bitmap olarak alıp kullanıyoruz ama veritabanına kaydederken veri olarak kaydederiz
        //bi yere kaydederken veri olarak kaydedilir resimler
        //byte dizisi olarak kaydedeceğiz
        //sqlite'da 1mb üstünde veri kullanırsak çöküyor o yüzden resmin kallitesi boyutu falan çok önemli
        if (bitmap!=null){
            val smallBitmap=kücült(bitmap!!,300)
            val outputStream = ByteArrayOutputStream()
            smallBitmap?.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            val byteArray = outputStream.toByteArray()
            veritabaninaKaydet(artName,artisName,year,byteArray)
        }

    }

    fun izinVarMıYokMu() {
        //izinler checkSelfPermission ile kontrol edilir.
        //contextCompat: izinlerin daha önceki android sürümleriyle uyumlu olmasını sağlıyor.
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
            //izin alıyoruz
        } else {
            //kullanıcı izin vermiş
            //direk galeriye gideceğiz
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentToGallery, 2)
        }
    }

    //izin verildiğinde
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //galeriyi açacağız
                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intentToGallery, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            //resim seçilmiş
            selectedPicture = data.data
            //şimdi bunu bitmape çevirelim
            if (selectedPicture != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver, selectedPicture!!)
                    bitmap = ImageDecoder.decodeBitmap(source)
                    selectImageView.setImageBitmap(bitmap)
                } else {
                    //getBitmap api28 sonrasında çalışmıyor
                    bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, selectedPicture)
                    selectImageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    fun kücült(image: Bitmap, maximumSize: Int): Bitmap {
        //şimdi görselin boyutunu da küçültelim
        //bitmapi küçültcez
        var width = image.width
        var height = image.height

        val bitmapRatio: Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1) {
            //görsel yatay
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    fun veritabaninaKaydet(artName:String,artisName:String,year:String,byteArray:ByteArray){
        try {
            val database=this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS arts(id INTEGER PRIMARY KEY,artname VARCHAR,artistname VARCHAR,year VARCHAR,image BLOB)")
            val sqlString="INSERT INTO arts(artname,artistname,year,image) VALUES(?,?,?,?)"
            val statement=database.compileStatement(sqlString)
            statement.bindString(1,artName)
            statement.bindString(2,artisName)
            statement.bindString(3,year)
            statement.bindBlob(4,byteArray)
            statement.execute()
        }catch (e:Exception){
            e.printStackTrace()
        }
        //finish()  //diyip bu aktiviteyi kapatmış oluruz. Main aktiviteye dönmüş oluruz.
        //finish yapınca main yenilenmiyo. Ama biz yenilenmesini istiyoruz o yüzden intent yapacağız
        val intent=Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)


    }

}
