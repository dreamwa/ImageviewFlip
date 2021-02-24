package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomImageview(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val TOP = 100f
    val LEFT = 200f
    var leftFlip = 0f
        set(value) {
            field = value

            invalidate()
        }
        get() {
            return field
        }

    var rightFlip = 0f
        set(value) {
            field = value

            invalidate()
        }
        get() {
            return field
        }
    var flipRotation = 0f
        set(value) {
            field = value

            invalidate()
        }
        get() {
            return field
        }

    var endFlip = 0f
        set(value) {
            field = value

            invalidate()
        }
        get() {
            return field
        }

    lateinit var bitmap: Bitmap
    val camera = Camera()

    init {
        bitmap = BitmapUtils.getAvatar(resources, Utils.dp2px(200f).toInt())
        camera.setLocation(0f, 0f, -8f)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //左半部分
        canvas.save()
        canvas.translate(-(-LEFT - bitmap.width / 2f), -(-TOP - bitmap.height / 2f))
//
        canvas.rotate(90f)
        camera.save()
        camera.rotateY(endFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.rotate(-90f)

        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateY(leftFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-bitmap.width  , -bitmap.height  , 0, bitmap.height )
        canvas.rotate(flipRotation)
        canvas.translate(-LEFT - bitmap.width / 2f, -TOP - bitmap.height / 2f)
        canvas.drawBitmap(bitmap, LEFT, TOP, paint)
        canvas.restore()

        //右半部分
        canvas.save()
        canvas.translate(-(-LEFT - bitmap.width / 2f), -(-TOP - bitmap.height / 2f))
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateY(rightFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(0, -bitmap.height, bitmap.width , bitmap.height )
        canvas.rotate(flipRotation)
        canvas.translate(-LEFT - bitmap.width / 2f, -TOP - bitmap.height / 2f)
        canvas.drawBitmap(bitmap, LEFT, TOP, paint)
        canvas.restore()


    }
}