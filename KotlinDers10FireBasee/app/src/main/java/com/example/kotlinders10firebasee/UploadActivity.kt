package com.example.kotlinders10firebasee

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload.*
import java.lang.Exception
import java.util.*

class UploadActivity : AppCompatActivity() {

    var selectedPicture:Uri?=null
    private lateinit var db:FirebaseFirestore
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        auth=FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        action()
    }

    fun action(){
        uploadImage.setOnClickListener{
            //galeri açılacak ama önce izin almamız gerekiyor
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

            }else{
                //galeriyi açabilirim
                val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,2)
            }
        }

        uploadButton.setOnClickListener {
            val uuid=UUID.randomUUID()
            val imageName="$uuid.jpg"

            //Görseli storage'a kaydedeceğiz.
            //Sonra bunun kaydedildiği web adresini alıp veritabanına firestore'a kaydedeceğiz
            val storage=FirebaseStorage.getInstance()
            val reference=storage.reference  //storage'ın hepsine bir reference oluşturduk
            //child bir alt satıra geç demek
            val imagesReference=reference.child("images").child(imageName)
            if (selectedPicture!=null){
                imagesReference.putFile(selectedPicture!!).addOnSuccessListener { taskSnapshot ->
                    //veritabanına kaydedeceğiz verilerimizi
                    //storagedaki resmimizin download url'ini alalım
                    val uploadedPictureReference=FirebaseStorage.getInstance().reference.child("images").child(imageName)
                    uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                        val downloadUrl=uri.toString()
                        var comment=uploadText.text.toString()

                        //verileri firestore'a hashmapleri kullanarak kaydediyoruz
                        val postMap= hashMapOf<String,Any>()
                        postMap.put("downloadUrl",downloadUrl)
                        postMap.put("comment",comment)
                        postMap.put("userEmail",auth.currentUser!!.email.toString())
                        postMap.put("date",Timestamp.now())  //timestamp firebase'e özel olanı seçtik

                        db.collection("Posts").add(postMap).addOnCompleteListener { task ->
                            if (task.isSuccessful&&task.isComplete){
                                val intent=Intent(this,FeedActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }.addOnFailureListener{exception ->
                            Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==1){
            if (grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //galeriyi açabilirim
                val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2&&resultCode== Activity.RESULT_OK&&data!=null){
            selectedPicture=data.data
            try {
                //uriyi bitmape çevireceğiz kullanabilmek için appte
                if (selectedPicture!=null){
                    if (Build.VERSION.SDK_INT>=28){
                        val source=ImageDecoder.createSource(contentResolver,selectedPicture!!)
                        val bitmap=ImageDecoder.decodeBitmap(source)
                        uploadImage.setImageBitmap(bitmap)
                    }else{
                        val bitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,selectedPicture)
                        uploadImage.setImageBitmap(bitmap)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
