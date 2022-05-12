package com.example.json
import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val nameArray: Array<String>,private val surnameArray: Array<String>,
                    private val emailArray: Array<String>,private val numberOfTaskArray: Array<Int>,private val numberOfPostsArray: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.element, nameArray) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.element, null, true)

        val name = rowView.findViewById(R.id.name) as TextView
        val surname = rowView.findViewById(R.id.Surrname) as TextView
        val email  = rowView.findViewById(R.id.Email) as TextView
        val numberOfTasks = rowView.findViewById(R.id.TaskNumber) as TextView
        val numberOfPosts = rowView.findViewById(R.id.PostNumber) as TextView

        name.text = nameArray[position]
        surname.text = surnameArray[position]
        email.text = emailArray[position]
        numberOfTasks.text = numberOfTaskArray[position].toString()
        numberOfPosts.text = numberOfPostsArray[position].toString()

        return rowView
    }
}
