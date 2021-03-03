package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MultiView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap

    var downX = 0f
    var downY = 0f

    var offsetX = 0f
    var offsetY = 0f

    var firstDownX = 0f
    var firstDownY = 0f

    //当前按下point的id
    var currentId = 0

    init {
        bitmap = BitmapUtils.getAvatar(resources, 200.dp2px().toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                currentId = event.getPointerId(0)
                Log.e("TAG", "ACTION_DOWN")
                firstDownX = offsetX
                firstDownY = offsetY
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val moveIndex = event.findPointerIndex(currentId)
                Log.e("TAG", "moveIndex==${moveIndex}")

                Log.e("TAG", "ACTION_MOVE")
                offsetX = event.getX(moveIndex) - downX + firstDownX
                offsetX = Math.min(offsetX, width.toFloat() - bitmap.width)
                offsetX = Math.max(offsetX, 0f)
                offsetY = event.getY(moveIndex) - downY + firstDownY
                offsetY = Math.min(offsetY, height.toFloat() - bitmap.height)
                offsetY = Math.max(offsetY, 0f)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                Log.e("TAG", "ACTION_UP")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                Log.e("TAG", "ACTION_POINTER_DOWN")
                val actionIndex = event.actionIndex
                currentId = event.getPointerId(actionIndex)
                firstDownX = offsetX
                firstDownY = offsetY
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)

            }
            MotionEvent.ACTION_POINTER_UP -> {
                Log.e("TAG", "ACTION_POINTER_UP")
                val actionIndex = event.actionIndex
                val pointId = event.getPointerId(actionIndex)
                var newIndex = 0
                if (pointId == currentId) {
                    if (actionIndex == event.pointerCount - 1) {
                        newIndex = event.pointerCount - 2
                    } else {
                        newIndex = event.pointerCount - 1
                    }
                    currentId = event.getPointerId(newIndex)
                    firstDownX = offsetX
                    firstDownY = offsetY
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                }
            }


        }
        return true
    }
}