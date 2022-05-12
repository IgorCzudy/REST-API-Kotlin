package com.example.json

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.json.R.id.button

class NextActivity  : AppCompatActivity(){

    private val topLeftArray: MutableList<String> = mutableListOf<String>()
    private val topRightArray: MutableList<String> = mutableListOf<String>()
    private val bodyArray: MutableList<String> = mutableListOf<String>()
    private val idArray: MutableList<Int> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twolists)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val listView = findViewById(R.id.listView) as ListView

        val button = findViewById(button) as Button
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getTodos()
        viewModel.myResponseListOfTodos.observe(this) {
            for (todo in it) {
                if (message != null) {
                    if (todo.userId == message.toInt()) {
                        topRightArray.add(todo.completed)
                        bodyArray.add(todo.title)
                        topLeftArray.add("ToDoes")
                        idArray.add(todo.userId)
                    }
                }
            }
            viewModel.getPosts()
            viewModel.myResponseListOfPosts.observe(this) {
                for (post in it) {
                    if (post.userId == message?.toInt()) {
                        topRightArray.add(post.title)
                        bodyArray.add(post.body)
                        topLeftArray.add("Post")
                        idArray.add(post.userId)
                    }
                }

                val myListAdapter = MyListAdapter2(
                    this, topLeftArray.toTypedArray(), topRightArray.toTypedArray(),
                    bodyArray.toTypedArray()
                )
                listView.adapter = myListAdapter

                listView.setOnItemClickListener() { adapterView, view, position, id ->
                    val itemAtPos = adapterView.getItemAtPosition(position)
                    if (itemAtPos.equals("Post")) {
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        val intent = Intent(this, SecendActivity::class.java).apply {
                            putExtra(EXTRA_MESSAGE, itemIdAtPos.toString() + " "
                                    + idArray[itemIdAtPos.toInt()].toString())
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
