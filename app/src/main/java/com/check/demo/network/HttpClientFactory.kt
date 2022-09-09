package com.check.demo.network

import okhttp3.OkHttpClient

object HttpClientFactory {

    fun getHttpClient() =
        OkHttpClient.Builder()
            .build()
}
