package com.example.kotlinders10firebasee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

/**
 * firebase'de kullanmak istediklerimizi buradan bir bağlantı yaptık. Otomatik gradle eklemeleri geldi
 * ama ilk kullanmadan önce firebasede de etkinleştirmemiz gerekiyor her modülü
 */
class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    //private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()
        val user=auth.currentUser
        if (user!=null){
            val intent= Intent(applicationContext,FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
        signUp()
        signIn()

    }

    fun signUp(){
        signUpButton.setOnClickListener {
            val email=userEmailEditText.text.toString()
            val password=passwordEditText.text.toString()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val intent= Intent(applicationContext,FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener{exception ->
                if(exception!=null){
                    Toast.makeText(applicationContext,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    fun signIn(){
        signInButton.setOnClickListener {
            val email=userEmailEditText.text.toString()
            val password=passwordEditText.text.toString()
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(applicationContext,"Hosgeldin ${auth.currentUser!!.email.toString()}", Toast.LENGTH_LONG).show()
                    val intent= Intent(applicationContext,FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener{exception ->
                Toast.makeText(applicationContext,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()

            }

        }
    }



}
