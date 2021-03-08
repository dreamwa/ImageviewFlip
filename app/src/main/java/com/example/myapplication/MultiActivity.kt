package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MultiActivity : AppCompatActivity() {
    lateinit var sign: MultiView2
    lateinit var clear: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)

        sign = findViewById(R.id.sign)
        clear = findViewById(R.id.clear)
        clear.setOnClickListener {
            sign.alear()
        }
    }
}