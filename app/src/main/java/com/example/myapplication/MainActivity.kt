package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ani1 =
            ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.view), "rightFlip", -45f).apply {
                duration = 1500
                startDelay = 1000
            }

        val ani2 =
            ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.view), "flipRotation", 270f).apply {
                duration = 1500
                startDelay = 1000
            }
        val ani3 =
            ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.view), "endFlip", 50f).apply {
                duration = 1500
                startDelay = 1000
            }

        val set = AnimatorSet()
        set.playSequentially(ani1, ani2, ani3)
        set.start()
    }
}