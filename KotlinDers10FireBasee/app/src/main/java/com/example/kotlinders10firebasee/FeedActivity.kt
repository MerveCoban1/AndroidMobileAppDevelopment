package com.example.kotlinders10firebasee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_feed.*

/**
 * listelerken her bir listelediğim veri için etrafına border çizmek istiyorum bunun için
 * drawable-new-drawable resource file-border_stroke-ok
 *
 */
class FeedActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseFirestore

    var userEmailFromDb:ArrayList<String> =ArrayList()  //boş bir arraylist tanımladık
    var userCommentFromDb:ArrayList<String> = ArrayList()
    var userImageFromDb:ArrayList<String> = ArrayList()

    var adapter:FeedRecyclerAdapter?=null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.add_post){
            val intent=Intent(applicationContext,UploadActivity::class.java)
            startActivity(intent)
        }else if (item.itemId==R.id.exit){
            auth.signOut()
            val intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        auth=FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()
        getDataFromDatabase()
        listele()

    }

    fun getDataFromDatabase(){
/*
        //belirli bir documentteki idyi vererek içindeki verileri çekebiliriz
        database.collection("Posts").document("djshjhjfhs").get().addOnCompleteListener {  }
        //filtreleme yapabiliriz
        database.collection("Posts").whereEqualTo("userEmail","merve@gmail.com").addSnapshotListener()
*/

        database.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(applicationContext,error.localizedMessage.toString(),Toast.LENGTH_LONG).show()

            }else{
                if (value!=null){
                    if (!value.isEmpty){
                        userEmailFromDb.clear()
                        userCommentFromDb.clear()
                        userImageFromDb.clear()

                        val documents=value.documents
                        for (document in documents){
                            val comment=document.get("comment") as String
                            val userEmail=document.get("userEmail") as String
                            val downloadUrl=document.get("downloadUrl") as String
                            val timestamp=document.get("date") as Timestamp
                            val date=timestamp.toDate()

                            userEmailFromDb.add(userEmail)
                            userCommentFromDb.add(comment)
                            userImageFromDb.add(downloadUrl)

                            adapter!!.notifyDataSetChanged()

                            //listView'ın bu tarz yerlerde kullanılması pek önerilmiyor. O yüzden recyclerView kullanacağız

                        }
                    }
                }
            }
        }
    }

    fun listele(){
        var layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        adapter=FeedRecyclerAdapter(userEmailFromDb,userCommentFromDb,userImageFromDb)
        recyclerView.adapter=adapter

    }

}
