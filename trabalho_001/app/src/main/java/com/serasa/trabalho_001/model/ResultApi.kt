package com.serasa.trabalho_001.model

import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("results")
    val resultApi: List<User>
)
