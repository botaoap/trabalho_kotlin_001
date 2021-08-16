package com.serasa.trabalho_001.modelArticle

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("imageUrl")
    val image: String,
    @SerializedName("newsSite")
    val news: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("publishedAt")
    val published: String,
    @SerializedName("updatedAt")
    val update: String,
    @SerializedName("featured")
    val featured: Boolean

)