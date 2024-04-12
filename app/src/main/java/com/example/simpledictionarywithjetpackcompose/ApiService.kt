package com.example.simpledictionarywithjetpackcompose

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private  val retrofit = Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val dictionaryResponse = retrofit.create(ApiService::class.java)

interface ApiService{

    @GET("{word}")

    suspend fun getDefinition(@Path("word") defination: String) : Response<List<WordResult>>

}