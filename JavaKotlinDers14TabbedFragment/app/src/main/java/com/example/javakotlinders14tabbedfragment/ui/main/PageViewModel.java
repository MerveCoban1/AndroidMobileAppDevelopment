package com.example.javakotlinders14tabbedfragment.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    //fragmanlar içersinde kullanacağımız verilerin bu sınıf içersinde tutulmasına yarıyor.
    private MutableLiveData<String> nameInput=new MutableLiveData<>();
    public void setName(String name){
        nameInput.setValue(name);
    }
    public LiveData<String> getName(){
        return nameInput;
    }

}