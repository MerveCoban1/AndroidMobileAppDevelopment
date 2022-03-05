package com.example.kotlinders15workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * amacımız bunu uygulama kapalıyken de çalıştırmak
 * Mainden buraya bir veri göndermek istiyorsak: val getData=inputData kullanıyoruz
 *
 */
class RefreshDatabase(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {

    override fun doWork(): Result {
        val getData=inputData
        val myNumber=getData.getInt("intKey",0)

        refreshDatabase(myNumber)
        return Result.success()
    }

    private fun refreshDatabase(myNumber: Int){
        val sharedPreferences=context.getSharedPreferences("com.example.kotlinders15workmanager",Context.MODE_PRIVATE)
        var mySavedNumber=sharedPreferences.getInt("myNumber",0)
        mySavedNumber+=myNumber
        println(mySavedNumber)
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply()

    }
}