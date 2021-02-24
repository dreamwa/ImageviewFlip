package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class ConfigActivity : AppCompatActivity() {
    lateinit var configBean: ConfigBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)


//        val locationService = ConfigBean.LocationService(true)
//
//        val navigationService = ConfigBean.NavigationService(true, "baidu")
//
//        val updateService = ConfigBean.UpdateService(true, "www.upload.com")
//
//        val imageLoadService = ConfigBean.ImageLoadService(true, "glide")
//
//        val imageWaterMarkService = ConfigBean.ImageWaterMarkService(false)
//
//        val speechRecognitionService = ConfigBean.SpeechRecognitionService(true, "content")
//
//        val speechSpeakService = ConfigBean.SpeechSpeakService(true, "content")
//
//        configBean = ConfigBean(locationService,navigationService,updateService,imageLoadService, imageWaterMarkService, speechRecognitionService, speechSpeakService)
//
//        val json = Gson().toJson(configBean)
//        Log.e("TAG","json==${json}")

        var json = Utils.getJson(this, "config.json")
        Log.e("TAG","json==${json}")
//
//        val configBean = Utils.jsonToObject(json,ConfigBean::class.java)
//        Log.e("TAG","configBean==${configBean}")

//        val config = ImApplication.getConfig()
//        Log.e("TAG","config==${config}")

//        val result = Utils.getLocation()
//        Log.e("TAG", "result==${result}")
    }
}