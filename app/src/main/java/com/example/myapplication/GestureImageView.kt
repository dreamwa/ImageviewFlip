package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat

class GestureImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap

    //初始偏移，为了图片居中
    var firstOffsetX = 0f
    var firstOffsetY = 0f


    //第二次偏移，图片跟着手势移动
    var secondOffsetX = 0f
    var secondOffsetY = 0f

    //内贴边
    var smallScale = 0f

    //外贴边
    var bigScale = 0f

    //外贴边再放大的倍数
    val bigRatio = 1.5f

    var gustureDetector: GestureDetectorCompat
    var overScroller: OverScroller
    var isBig = false


    //动画参数
    var scaleOffest = 0f   //0..1
        set(value) {
            field = value
            invalidate()
        }
        get() {
            return field
        }
    lateinit var scaleAnimator: ObjectAnimator

    init {
        bitmap = BitmapUtils.getAvatar(resources, 200.dp2px().toInt())

        gustureDetector = GestureDetectorCompat(context, this)
        gustureDetector.setIsLongpressEnabled(false)
        overScroller = OverScroller(context)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        firstOffsetX = (width.toFloat() - bitmap.width) / 2
        firstOffsetY = (height.toFloat() - bitmap.height) / 2

        if (bitmap.width.toFloat() / bitmap.height > width.toFloat() / height) {
            smallScale = width.toFloat() / bitmap.width
            bigScale = height.toFloat() / bitmap.height * bigRatio
        } else {
            smallScale = height.toFloat() / bitmap.height
            bigScale = width.toFloat() / bitmap.width * bigRatio
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gustureDetector.onTouchEvent(event)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(secondOffsetX, secondOffsetY)


        var scale = smallScale + (bigScale - smallScale) * scaleOffest
        canvas.scale(scale, scale, width.toFloat() / 2, height.toFloat() / 2)
        canvas.drawBitmap(bitmap, firstOffsetX, firstOffsetY, mPaint)
    }


    override fun onDown(e: MotionEvent?): Boolean {
        Log.e("TAG", "onDown")
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.e("TAG", "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.e("TAG", "onSingleTapUp")
        return true
    }


    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.e("TAG", "onFling")
        when (isBig) {
            true -> {
                overScroller.fling(
                    secondOffsetX.toInt(),
                    secondOffsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    -(bitmap.width * bigScale - width).toInt() / 2,
                    (bitmap.width * bigScale - width).toInt() / 2,
                    -(bitmap.height * bigScale - height).toInt() / 2,
                    (bitmap.height * bigScale - height).toInt() / 2,
                    30.dp2px().toInt(),
                    30.dp2px().toInt()
                )

                postOnAnimation(this)
            }
        }

        return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        when (isBig) {
            true -> {
                secondOffsetX -= distanceX
                Log.e(
                    "TAG",
                    "bitmapWidth==${bitmap.width},bigScale==${bigScale},bigRatio==${bigRatio},width==${width}"
                )
                Log.e("TAG", "secondOffsetX1==${secondOffsetX}")
                secondOffsetX = Math.min(secondOffsetX, (bitmap.width * bigScale - width) / 2)
                secondOffsetX = Math.max(secondOffsetX, -(bitmap.width * bigScale - width) / 2)

                secondOffsetY -= distanceY
                secondOffsetY = Math.min(secondOffsetY, (bitmap.height * bigScale - height) / 2f)
                secondOffsetY = Math.max(secondOffsetY, -(bitmap.height * bigScale - height) / 2f)
                invalidate()
                Log.e("TAG", "bitmapWidth==${bitmap.width}")
            }
            else -> {
                Log.e("TAG", "bitmapWidth==${bitmap.width}")
            }
        }
        Log.e(
            "TAG",
            "onScroll,x1==${e1.x},rawX1==${e1.rawX},y1==${e1.y},rawY1==${e1.rawY},distanceX==${distanceX}"
        )
        Log.e(
            "TAG",
            "onScroll,x2==${e2.x},rawX2==${e2.rawX},y2==${e2.y},rawY2==${e2.rawY},distanceY==${distanceY}"
        )
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.e("TAG", "onLongPress")
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.e("TAG", "onDoubleTap")
        isBig = !isBig
        when (isBig) {
            true -> {
                getMyScaleAnimator().start()
            }
            else -> {
                getMyScaleAnimator().reverse()
                secondOffsetX = 0f
                secondOffsetY = 0f
            }
        }

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.e("TAG", "onDoubleTapEvent")
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.e("TAG", "onSingleTapConfirmed")
        return true
    }

    fun getMyScaleAnimator(): ObjectAnimator {
        if (!this::scaleAnimator.isInitialized) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleOffest", 0f, 1f)
        }
        return scaleAnimator
    }

    override fun run() {
        if (overScroller.computeScrollOffset()) {
            secondOffsetX = overScroller.currX.toFloat()
            secondOffsetY = overScroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
        }

    }


}
