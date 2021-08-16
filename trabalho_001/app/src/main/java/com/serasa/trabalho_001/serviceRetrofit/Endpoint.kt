package com.serasa.trabalho_001.serviceRetrofit

import com.serasa.trabalho_001.model.ResultApi
import com.serasa.trabalho_001.modelArticle.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Endpoint {
    @GET("/api/")
    fun getUsers(): Call<ResultApi>

    @GET("/v3/articles")
    fun getArtcicle(): Call<List<Article>>

    @GET("/v3/articles/{id}")
    fun getAritcleById(@Path ("id") resId: Int) : Call<Article>

}