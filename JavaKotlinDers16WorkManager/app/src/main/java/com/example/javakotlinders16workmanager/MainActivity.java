package com.example.javakotlinders16workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

/**
 * bir jatpaack öğesidir.
 * hem asenkron çalışıyo hem de uygulama kapansa bile arkaplanda çalışıyor.
 * gradle:app kısmına eklemelerimizi yapıyoruz.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder().putInt("intKey", 1).build(); //veriyi bu şekilde yolluyoruz.
        //datayı ve constraint'i oluştururken androidx.work olması lazım dikkat et implement ederken
        Constraints constraints = new Constraints.Builder()
                // .setRequiredNetworkType(NetworkType.CONNECTED)   //bağlantısını kontrol ediyoruz.
                .setRequiresCharging(false)  //şarja bağlı olmak zorunda değil.
                .build();
//sadece bir defaya mahsus yapılsın istediğimden dolayı onetime olanı seçtim
 /*       WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                //.setInitialDelay(5, TimeUnit.MINUTES)   //5dk sonra olsun falan diyebiliyoz.
                //.addTag("myTag")
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);*/
//birden fazla istediğimiz periyotta çalışacak periyodik workrequesti görelim
        //en az 15 dkda çalışıyormuş 1 dk da yazabiliriz ama çalışmazmış
        WorkRequest workRequest = new PeriodicWorkRequest.Builder(RefreshDatabase.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);

        //durumun ne olduğunu bildirim gelerek falan görmek istiyorsak.
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo.getState() == WorkInfo.State.RUNNING) {
                    System.out.println("running");
                } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println("success");
                } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                    System.out.println("failed");
                }
            }
        });
        //nasıl iptal edebiliriz
        WorkManager.getInstance(this).cancelAllWork();//hepsini iptal et demek

        //Chaining
        //periyodik olan iş isteklerini birbirine bağlayamıyoruz
        //birbirine sadece bir defa yaptıklarımızı bağlayabiliyoruz.

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)  //bununla başla sonra..
                .then(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)  //bu üçünün farklı olduklaarını düşünsırayla önce şu sonra bu çalışsın diyebiliyoruz.
                .enqueue();
    }
}
