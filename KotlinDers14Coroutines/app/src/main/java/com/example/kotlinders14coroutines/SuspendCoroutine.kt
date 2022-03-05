package com.example.kotlinders14coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Suspend Function: içersinde coroutine çalıştırılabilen fonksiyondur.
 * Bu fonksiyonlar suspend edilebilir yani istediğimiz zaman durdurup çalıştırılabilir
 *
 */

fun main() {
    //mesela burda myFunction() 'ı çağıramam başka bir suspend function ya da diğer şeyler içinde çağırılabilir.
    //diğer şeylerden kastım coroutine'li şeyler
}

suspend fun myFunction() {
    coroutineScope { //ya suspend fonksiyonda ya da o diğer şeylerin içinde çağırılabiliyor.
        delay(4000)
        println("suspend function")
    }
}
