package com.serasa.trabalho_001.model

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
)
