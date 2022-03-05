package com.example.kotlinders14coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    //örneğin iki tane veri çekiyoruz internetten
    //async dediğimizde bir cevap bekliyo
    var userName=""
    var userAge=0
    runBlocking {

        val downloadedName=async {
            downloadName()
        }

        val downloadedAge=async {
            downloadAge()
        }
        //async: launch gibi çalışıyor ama bir return bekliyor
        userName= downloadedName.await() //theread'i beklemedn bunların inmesini bekliyor
        userAge=downloadedAge.await()


    }

}

suspend fun downloadName(): String{
    delay(2000)
    val userName="Merve"
    println("username download")
    return userName

}

suspend fun downloadAge(): Int{
    delay(2000)
    val userAge=25
    println("userage download")
    return userAge
}