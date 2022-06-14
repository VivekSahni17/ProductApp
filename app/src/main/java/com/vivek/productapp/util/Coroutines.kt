package com.vivek.productapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {

    fun <T : Any> ioToMainThread(work: suspend (() -> T?), callback: (T?) -> Unit) =

        CoroutineScope(Dispatchers.Main).launch {

            val data = CoroutineScope(Dispatchers.IO).async mukesh@{

                return@mukesh work()
            }.await()

            callback(data)
        }


}