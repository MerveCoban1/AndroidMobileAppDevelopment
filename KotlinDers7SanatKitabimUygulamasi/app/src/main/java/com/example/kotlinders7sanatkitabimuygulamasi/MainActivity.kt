package com.example.kotlinders7sanatkitabimuygulamasi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //bir xml dosyası yaptığımızda bir yerde kullanıp göstereceksek buna inflate deniyor
        val menuINflater = menuInflater
        menuINflater.inflate(R.menu.add_art, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_art_item) {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val artNameList = ArrayList<String>()
        val artIdList = ArrayList<Int>()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, artNameList)
        listView.adapter = arrayAdapter

        try {
            val database = this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE, null)
            val cursor = database.rawQuery("SELECT * FROM arts", null)
            val nameIx = cursor.getColumnIndex("artname")
            val idIx = cursor.getColumnIndex("id")
            while (cursor.moveToNext()) {
                artNameList.add(cursor.getString(nameIx))
                artIdList.add(cursor.getInt(idIx))
            }
            arrayAdapter.notifyDataSetChanged()
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, Main2Activity::class.java)
                intent.putExtra("info", "old")
                intent.putExtra("id", artIdList[position])
                startActivity(intent)
            }


    }
}
