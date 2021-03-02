package com.example.myapplication

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.marginLeft

class FlowViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    val childrenBounds = arrayListOf<Rect>()
    val MARGINLR = 5.dp2px()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var usedWidth = 0
        var usedHeight = 0
        for (i in 0..childCount - 1) {
            val childView = getChildAt(i)
            measureChildWithMargins(
                childView,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                usedHeight
            )


            var rect = Rect()
            if (childrenBounds.size <= i) {
                childrenBounds.add(rect)
            } else {
                rect = childrenBounds[i]
            }
            rect.set(
                usedWidth,
                usedHeight,
                usedWidth + childView.measuredWidth,
                usedHeight + childView.measuredHeight
            )

            usedWidth += (childView.measuredWidth + 2 * MARGINLR).toInt()
            usedHeight = (childView.measuredHeight).toInt()
        }
        val width = usedWidth
        val height = usedHeight
        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0..childCount - 1) {
            val child = getChildAt(i)
            val childBounds = childrenBounds[i]
            child.layout(
                (childBounds.left + MARGINLR).toInt(),
                (childBounds.top).toInt(),
                (childBounds.right + MARGINLR).toInt(),
                (childBounds.bottom).toInt()
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}