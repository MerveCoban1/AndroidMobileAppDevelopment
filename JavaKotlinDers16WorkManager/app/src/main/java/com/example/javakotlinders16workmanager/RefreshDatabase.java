package com.example.javakotlinders16workmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    Context myContext;

    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext=context;
    }

    @NonNull
    @Override
    public Result doWork() {//workmanager'ın ne yapacağını bu metot altına yapıyoruz.
        Data data=getInputData();//bize bir girdi verilecek onu al demek
        int myNumber=data.getInt("intKey",0);
        refreshDatabase(myNumber);

        return Result.success();
    }

    private void refreshDatabase(int myNumber){
        SharedPreferences sharedPreferences=myContext.getSharedPreferences("com.example.javakotlinders16workmanager",Context.MODE_PRIVATE);
        int mySavedNumber=sharedPreferences.getInt("mynumber",0);
        mySavedNumber+=myNumber;
        sharedPreferences.edit().putInt("mynumber",mySavedNumber).apply();
    }
}
