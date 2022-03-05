package com.example.kotlinders9seyahatkitabim

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.custom_list_row.view.*

class CustomAdapter(private val placeList:ArrayList<Place>,private val context: Activity) :
    ArrayAdapter<Place>(context, R.layout.custom_list_row,placeList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //buralarda context'e ya da placelist'e ulaşabilmek için başına val yazmamız gerekiyor
        val layoutInflater = context.layoutInflater
        val customView = layoutInflater.inflate(R.layout.custom_list_row, null, true)

        customView.textView.text = placeList.get(position).address



        return customView
    }

}