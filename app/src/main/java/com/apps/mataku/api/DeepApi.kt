package com.apps.mataku.api

import com.apps.mataku.BuildConfig.BASE_URL_DEEP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface DeepApi {
    @Multipart
    @POST("uploaderpterigium")
    fun uploadPterigium(@Part("file") file: String): Call<DeepResponse>

    @Multipart
    @POST("uploaderkatarak")
    fun uploadKatarak(@Part("file") file: String): Call<DeepResponse>

    companion object Factory {
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var client =  OkHttpClient.Builder().addInterceptor(interceptor).build()

        val BASE_URL = BASE_URL_DEEP
        fun create(): DeepApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(DeepApi::class.java)
        }
    }
}