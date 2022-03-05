package com.example.kotlinders14coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    runBlocking {
        launch(Dispatchers.Default) {
            println("context: ${coroutineContext}")
            withContext(Dispatchers.IO){
                println("context: ${coroutineContext}")
            }
        }
    }
}