package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_touch.*

class  TouchActivity : AppCompatActivity() {
    val lists = arrayListOf<String>()
    lateinit var rvAdapter : RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        for (i in 0..20){
            lists.add("iiiiii")
        }

        rvAdapter = RvAdapter(this,lists)
        r1.layoutManager = LinearLayoutManager(this)
        r2.layoutManager = LinearLayoutManager(this)

        r1.adapter = rvAdapter
        r2.adapter = rvAdapter

    }
}