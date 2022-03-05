package com.example.kotlinders5listview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //listview'ler ve arraylistler index mantığıyla çalıştığı için
    //beraber kullanmak çok mantıklıdır. Yani listview'ın 0.indexindeki  de senin
    //arraylistindeki 0.indexle aynı olacak.
    var landMarkNames = ArrayList<String>()
    val landMarkImages = ArrayList<Bitmap>()
    var landMarkImagesId=ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verileriTanimla()
        //resimTanimla()
        resimleriTanimla2()
        listViewOrnek()
        //action()
        action2()





    }

    fun verileriTanimla() {
        landMarkNames.add("pisa")
        landMarkNames.add("colosseum")
        landMarkNames.add("eiffel")
        landMarkNames.add("london bridge")
    }

    fun resimTanimla() {
        //image
        //bitmapa çevirelim
        val pisa = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.pisa)
        val colosseum =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.colosseum)
        val eiffel = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.eiffel)
        val bridge =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.londonbridge)
        landMarkImages.add(pisa)
        landMarkImages.add(colosseum)
        landMarkImages.add(eiffel)
        landMarkImages.add(bridge)
        //intent ile bitmapi yollayamayız çünkü bitmap büyük boyutludur.
    }

    fun resimleriTanimla2(){
        //image- efficient
        val pisaId=R.drawable.pisa
        val colosseumId=R.drawable.colosseum
        val eiffelId=R.drawable.eiffel
        val bridgeId=R.drawable.londonbridge

        landMarkImagesId.add(pisaId)
        landMarkImagesId.add(colosseumId)
        landMarkImagesId.add(eiffelId)
        landMarkImagesId.add(bridgeId)
    }

    fun listViewOrnek() {
        //adapter: layout ve data gösteriyoruz. kendimiz de yapabiliriz hazır da kullanabiliriz.Şimdi hazır kullanacağız
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, landMarkNames)
        listView.adapter = adapter
    }

    fun listViewOrnek2() {
        //kendimiz oluşturmak istersek önce bir layout oluşturmamız gerekiyor.
        val adapter = ArrayAdapter(this, R.layout.list_row_layout, R.id.textView2, landMarkNames)
        listView.adapter = adapter
    }

    fun action() {
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(applicationContext, DetailsActivity::class.java)
                intent.putExtra("name", landMarkNames[position])
                val singleton = Singleton.Selected
                singleton.selectedImage = landMarkImages[position]
                startActivity(intent)
            }
    }

    fun action2() {
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(applicationContext, DetailsActivity::class.java)
                intent.putExtra("name", landMarkNames[position])
                intent.putExtra("image",landMarkImagesId[position])
                //resmin idsini integer olarak aktarıyorum sadece
                startActivity(intent)
            }
    }
}
