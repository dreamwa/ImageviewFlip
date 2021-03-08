package com.example.myapplication

import android.content.Context
import android.content.pm.PackageItemInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()

    init {
        paint.color = Color.RED
        paint.strokeWidth = 2.dp2px()
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_UP -> {
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
            }

            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)
    }

    fun alear() {
        path.reset()
        invalidate()
    }

}