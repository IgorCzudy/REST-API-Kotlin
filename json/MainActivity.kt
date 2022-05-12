package com.example.json

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    var name: MutableList<String> = mutableListOf<String>()
    var surname: MutableList<String> = mutableListOf<String>()
    var email: MutableList<String> = mutableListOf<String>()
    var numberOfTask: MutableList<Int> = mutableListOf<Int>()
    var numberOfPosts: MutableList<Int> = mutableListOf<Int>()
    var id: MutableList<Int> = mutableListOf<Int>()

    private val todoesList: MutableList<Todos> = mutableListOf<Todos>()
    private val usersList: MutableList<User> = mutableListOf<User>()
    private val postsList: MutableList<Post> = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById(R.id.listView) as ListView

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUsers()
        viewModel.myResponseList.observe(this) {
            for (user in it) {
                val strs = user.name.split(" ").toTypedArray()
                val u = User(
                    id = user.id,
                    name = strs[0],
                    surname = strs[1],
                    email = user.email,
                    numberOfTask = 0,
                    numberOfPosts = 0
                )
                usersList.add(u)
            }

            viewModel.getPosts()
            viewModel.myResponseListOfPosts.observe(this) {
                for (post in it) {
                    postsList.add(post)
                }

                viewModel.getTodos()
                viewModel.myResponseListOfTodos.observe(this) {
                    for (todo in it) {
                        todoesList.add(todo)
                    }

                    setNumberOfPost()
                    setNumberOfTask()
                    setUser()

                    val myListAdapter = MyListAdapter(
                        this, name.toTypedArray(),
                        surname.toTypedArray(),
                        email.toTypedArray(),
                        numberOfTask.toTypedArray(),
                        numberOfPosts.toTypedArray()
                    )
                    listView.adapter = myListAdapter

                    listView.setOnItemClickListener() { adapterView, view, position, id ->
                        adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        val intent = Intent(this, NextActivity::class.java).apply {
                            putExtra(EXTRA_MESSAGE, itemIdAtPos.toString())
                        }
                        startActivity(intent)

                    }
                }
            }
        }
    }

    private fun setNumberOfPost(){
        for (user in usersList) {
            for (post in postsList) {
                if (user.id == post.userId) user.numberOfPosts++
            }
        }
    }
    private fun setNumberOfTask(){
        for (user in usersList) {
            for (todoes in todoesList) {
                if (user.id == todoes.userId) user.numberOfTask++
            }
        }
    }
    private fun setUser(){
        for (user in usersList) {
            id.add(user.id)
            name.add(user.name)
            surname.add(user.surname)
            email.add(user.email)
            numberOfTask.add(user.numberOfTask)
            numberOfPosts.add(user.numberOfPosts)
        }
    }
}
