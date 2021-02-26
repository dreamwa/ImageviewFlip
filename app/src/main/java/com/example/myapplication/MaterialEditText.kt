package com.example.myapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import java.util.HashMap

class MaterialEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    //字体大小
    val materialTextSize = Utils.dp2px(12f)

    //字体所用高度
    val materialTop = Utils.dp2px(18f)

    //字体距顶部padding
    val materialPaddingTop = Utils.dp2px(5f)

    //字体距左部padding
    val materialPaddingLeft = Utils.dp2px(5f)

    //字体内容
    val leftTopContent = hint.toString()

    //字体是否在显示状态
    var isContentShowing = false

    val bounds = Rect()

    //字体上下偏移
    val textOffset = Utils.dp2px(20f)

    //动画
    lateinit var animator: ObjectAnimator

    //透明度
    var materialAlpha = 0f
        set(value) {
            field = value
            invalidate()
        }
        get() {
            return field
        }


    init {
        setPadding(
            paddingLeft,
            materialTop.toInt() + paddingTop,
            paddingRight,
            paddingBottom
        )
        mPaint.textSize = materialTextSize

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isContentShowing && !TextUtils.isEmpty(s.toString())) {
                    isContentShowing = true
                    getAlphaAnimator()?.start()
                } else if (isContentShowing && TextUtils.isEmpty(s.toString())) {
                    isContentShowing = false
                    getAlphaAnimator()?.reverse()
                }
            }
        })

    }

    fun getAlphaAnimator(): ObjectAnimator? {
        if (!this::animator.isInitialized) {
            animator = ObjectAnimator.ofFloat(this@MaterialEditText, "materialAlpha", 0f, 1f)
        }
        return animator
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPaint.getTextBounds(leftTopContent, 0, leftTopContent.length, bounds)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.alpha = (255 * materialAlpha).toInt()
        var offset = (1 - materialAlpha) * textOffset
        canvas.drawText(
            leftTopContent,
            materialPaddingLeft,
            -(bounds.top + bounds.bottom).toFloat() + materialPaddingTop + offset,
            mPaint
        )
    }


}