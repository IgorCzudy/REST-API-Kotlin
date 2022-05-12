package com.example.json

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id : Int,
    @SerializedName("name")val name : String,
    @SerializedName("surname")val surname: String,
    @SerializedName("email")val email : String,
    @SerializedName("numberOfTask") var numberOfTask: Int,
    @SerializedName("numberOfPosts") var numberOfPosts: Int
)
