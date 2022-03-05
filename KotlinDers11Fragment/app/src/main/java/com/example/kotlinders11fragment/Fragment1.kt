package com.example.kotlinders11fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * burada önemli olan onCreateView fonksiyonu
 * inflater: bir layout'u xml dosyasını kodumuzda bir yerde kullanmak istersek kullanıyoruz
 */
class Fragment1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_1, container, false)


        return view
    }

}
