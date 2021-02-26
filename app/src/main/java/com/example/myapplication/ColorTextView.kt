package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.random.Random

class ColorTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val bounds0 = Rect()
    val bounds1 = Rect()
    val bounds2 = Rect()
    val bounds3 = Rect()
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.GRAY)
    val texts = arrayOf("墩", "小明", "至尊宝", "小张小张")

    val PADDINGTB = 5.dp2px()
    val PADDINGLR = 10.dp2px()
    var offset = 0

    init {
        mPaint.textSize = 12.dp2px()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPaint.getTextBounds(texts[0], 0, texts[0].length, bounds0)
        mPaint.getTextBounds(texts[1], 0, texts[1].length, bounds1)
        mPaint.getTextBounds(texts[2], 0, texts[2].length, bounds2)
        mPaint.getTextBounds(texts[3], 0, texts[3].length, bounds3)

        offset = bounds1.top + bounds1.bottom
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = colors[Random.nextInt(colors.size)]

        val indexBounds = Random.nextInt(texts.size)
        when (indexBounds) {
            0 -> {
                canvas.drawRect(
                    0f,
                    0f,
                    bounds0.right + 2 * PADDINGLR,
                    -offset + 2 * PADDINGTB,
                    mPaint
                )
                mPaint.color = Color.WHITE
                canvas.drawText(
                    texts[indexBounds],
                    PADDINGLR-bounds0.left,
                    -offset.toFloat() + PADDINGTB,
                    mPaint
                )
            }
            1 -> {
                canvas.drawRect(
                    0f,
                    0f,
                    bounds1.right + 2 * PADDINGLR,
                    -offset + 2 * PADDINGTB,
                    mPaint
                )
                mPaint.color = Color.WHITE
                canvas.drawText(
                    texts[indexBounds],
                    PADDINGLR-bounds1.left,
                    -offset.toFloat() + PADDINGTB,
                    mPaint
                )
            }
            2 -> {
                canvas.drawRect(
                    0f,
                    0f,
                    bounds2.right + 2 * PADDINGLR,
                    -offset + 2 * PADDINGTB,
                    mPaint
                )
                mPaint.color = Color.WHITE
                canvas.drawText(
                    texts[indexBounds],
                    PADDINGLR-bounds2.left,
                    -offset.toFloat() + PADDINGTB,
                    mPaint
                )
            }
            3 -> {
                canvas.drawRect(
                    0f,
                    0f,
                    bounds3.right + 2 * PADDINGLR,
                    -offset + 2 * PADDINGTB,
                    mPaint
                )
                mPaint.color = Color.WHITE
                canvas.drawText(
                    texts[indexBounds],
                    PADDINGLR-bounds3.left,
                    -offset.toFloat() + PADDINGTB,
                    mPaint
                )
            }
        }


    }
}