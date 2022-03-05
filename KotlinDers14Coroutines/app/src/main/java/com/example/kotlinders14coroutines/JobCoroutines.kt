package com.example.kotlinders14coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    //Job:
    runBlocking {
        val myJob=launch {
            delay(1000)
            println("jjjjjjj")
            val secondJob=launch {
                delay(1000)
                println("jjjjjjj")
            }
        }
        myJob.invokeOnCompletion {
            //mesela artık myJob bittiğinde bir şey yazdırabilirim
            println("nskjdbjfb")
        }
        myJob.cancel() //myJob çalışmıyo
    }
}