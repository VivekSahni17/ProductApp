package com.vivek.productapp

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T : Any> makeApiRequest(work: suspend () -> Response<T>): T {

        val response = work.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.code().toString()
            )
        }
    }
}

