package com.example.kotlinders16foursquareparse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_locations.*

class LocationsActivity : AppCompatActivity() {

    private var namesArray=ArrayList<String>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.add_place,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.add_place){
            val intent=Intent(applicationContext,PlaceNameActivity::class.java)
            startActivity(intent)
        }else if (item.itemId==R.id.logOut){

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)
        //verileri çekelim
        getParseData()
        locationslistView.setOnItemClickListener { parent, view, position, id ->
            val intent=Intent(applicationContext,DetailActivity::class.java)
            intent.putExtra("name",namesArray[position])
            //seçilen yerin ismini diğer aktiviteye aktardık
            startActivity(intent)
        }
    }

    private fun getParseData(){
        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,namesArray)
        locationslistView.adapter=arrayAdapter

        val query=ParseQuery.getQuery<ParseObject>("Locations")
        query.findInBackground{objects, e ->
            if (e!=null){
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (objects.size>0){
                    namesArray.clear()
                    for (parseObject in objects){
                        val name=parseObject.get("name").toString()
                        namesArray.add(name)
                    }
                    arrayAdapter.notifyDataSetChanged()
                }
            }

        }


    }
}
