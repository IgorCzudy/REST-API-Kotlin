package com.example.json
import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter2(private val context: Activity, private val topLeftArray: Array<String>,
                     private val topRightArray: Array<String>,
                    private val bodyArray: Array<String>)
    : ArrayAdapter<String>(context, R.layout.post, topLeftArray) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.post, null, true)

        val topLeft = rowView.findViewById(R.id.lefttop) as TextView
        val topRight = rowView.findViewById(R.id.righttop) as TextView
        val body  = rowView.findViewById(R.id.body) as TextView

        topLeft.text = topLeftArray[position]
        topRight.text = topRightArray[position]
        body.text = bodyArray[position]

        return rowView
    }
}
