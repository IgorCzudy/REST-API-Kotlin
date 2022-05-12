package com.example.json

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val myResponseList: MutableLiveData<List<User>> = MutableLiveData()
    val myResponseListOfTodos: MutableLiveData<List<Todos>> = MutableLiveData()
    val myResponseListOfPosts: MutableLiveData<List<Post>> = MutableLiveData()
    val myResponseListOfComments: MutableLiveData<List<Comment>> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            myResponseList.value = UserNetwork.retrofit.getUsers()
        }
    }
    fun getTodos() {
        viewModelScope.launch {
            myResponseListOfTodos.value = UserNetwork.retrofit.getToDos()
        }
    }
    fun getPosts() {
        viewModelScope.launch {
            myResponseListOfPosts.value = UserNetwork.retrofit.getPosts()
        }
    }
    fun getComments() {
        viewModelScope.launch {
            myResponseListOfComments.value = UserNetwork.retrofit.getComments()
        }
    }
}
