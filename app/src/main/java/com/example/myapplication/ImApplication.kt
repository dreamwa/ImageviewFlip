package com.example.myapplication

import android.app.Application
import android.graphics.Bitmap
import android.util.Log

class ImApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        var json = Utils.getJson(this, "config.json")
        configBean = Utils.jsonToObject(json, ConfigBean::class.java)
    }

    companion object {
        lateinit var configBean: ConfigBean
        fun getConfig(): ConfigBean {
            return configBean
        }
    }
}