package com.example.myapplication

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapUtils {
    companion object{
        fun getAvatar(resources: Resources, width: Int): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(resources, R.mipmap.zhukai, options)
            options.inJustDecodeBounds = false
            options.inDensity = options.outWidth
            options.inTargetDensity = width
            return BitmapFactory.decodeResource(resources, R.mipmap.zhukai, options)
        }
    }
}