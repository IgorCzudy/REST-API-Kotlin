package com.example.json

import com.google.gson.annotations.SerializedName

data class Todos(
    @SerializedName("id") val id : Int,
    @SerializedName("userId") val userId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("completed") val completed : String
)