package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Utils {

    companion object {
        fun dp2px(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().displayMetrics
            )
        }

        fun getJson(context: Context, fileName: String): String {
            val stringBuilder = StringBuilder()
            //获得assets资源管理器
            val assetManager = context.assets
            //使用IO流读取json文件内容
            try {
                val bufferedReader = BufferedReader(
                    InputStreamReader(
                        assetManager.open(fileName), "utf-8"
                    )
                )
                var line: String = ""
                while (bufferedReader.readLine()?.also({ line = it }) != null) {
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return stringBuilder.toString()
        }

        fun <T> jsonToObject(json: String, clazz: Class<T>): T {
            val gson = Gson()
            return gson.fromJson(json, clazz)
        }


        fun getLocation() : String{
            when (ImApplication.getConfig().locationService.isOpen) {
                true -> {
                    Log.e("TAG", "定位配置开启，返回结果")

                    return "success!!!"
                }
                else -> {
                    Log.e("TAG", "定位配置关闭，提示异常")
                    return "failed!!!"
                }
            }
        }
    }
}