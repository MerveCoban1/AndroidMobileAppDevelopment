package com.example.kotlinders12navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Jetpack: Geliştiriciler için oluşturulmuş bazı kütüphaneler
 * Fragment de bir jetpack
 * uygulama içersindeki navigasyonu sağlıyor. Özelllikle fragmentlerde kullanılıyor
 *developer.android.com a git -jetpack-navigation-ordan dependencies kısımları için eklemelerini al(Kotlin için olanları)
 *Navigation kullanımı:
 -res-new-android resource file-resource type=Navigation seç!!-navigation_graph isimli bir xml doyası oluşturduk
fragmentlerimi o xml dosyasında istediğim gibi yerleştirebiliyorum
 -fragmente tıklayınca yan tarafta attributesler çıkıyor.
 -fragment 1 den 2 ye bir ok çıkardık bu aksiyon oluyor. Nereye gideceğini belirttik.
-MainActivity.xml dosyama navHostFragment ekledim ordan oluşturduğum navigation_graph isimli navigation'umu seçtim
 -birbirleri arasındaki geçişi fragment.kt sınıflarında yapıyoruz action oluşturarak.
 *Argümanlar:
 -fragmentler arasında veri göndermek istiyoruz. Mesela birinci fragmentden 2.ye bir veri aktarmak
 istiyoruz diyelim 2.fragmente bir argüman oluşturmamız gerekiyor.
 2.fragmente bas argüman ekle sanki bir değişken ekliyormuş gibi
 *
 *
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
