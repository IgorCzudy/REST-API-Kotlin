package com.example.json

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class SecendActivity : AppCompatActivity() {

    private val topLeftArray: MutableList<String> = mutableListOf<String>()
    private val topRightArray: MutableList<String> = mutableListOf<String>()
    private val bodyArray: MutableList<String> = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twolists)
        val m = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)?.split(" ")
        val message = m?.get(0)
        val message2 = m?.get(1)

        val listView = findViewById(R.id.listView) as ListView

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, message2.toString())
            }
            startActivity(intent)
        }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getComments()
        viewModel.myResponseListOfComments.observe(this) {
            for (todo in it) {
                if (message != null) {
                    if (todo.postId == message.toInt()){
                        topLeftArray.add("Comment")
                        topRightArray.add(todo.name)
                        bodyArray.add(todo.body)
                    }
                }

                val myListAdapter = MyListAdapter2(
                    this, topLeftArray.toTypedArray(), topRightArray.toTypedArray(),
                    bodyArray.toTypedArray()
                )
                listView.adapter = myListAdapter

            }
        }
    }
}