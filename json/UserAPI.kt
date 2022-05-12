package com.example.json

import retrofit2.Call
import retrofit2.http.GET

interface UserAPI {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("todos")
    suspend fun  getToDos(): List<Todos>


    @GET("posts")
    suspend fun  getPosts(): List<Post>

    @GET("comments")
    suspend fun  getComments(): List<Comment>

}
