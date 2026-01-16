package com.infilect.assignment.retailscanner

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class GraphicOverlay(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lock = Any()
    // Stores unique product locations to prevent re-marking
    private val detectedProducts = mutableSetOf<RectF>()

    private val paintTick = Paint().apply {
        color = Color.GREEN
        textSize = 80f
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        isFakeBoldText = true
    }

    private val paintBox = Paint().apply {
        color = Color.CYAN
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    fun addResults(newRects: List<Rect>, imageWidth: Int, imageHeight: Int) {
        synchronized(lock) {
            val scaleX = width.toFloat() / imageHeight.toFloat() // Rotation adjustment
            val scaleY = height.toFloat() / imageWidth.toFloat()

            for (rect in newRects) {
                val mappedRect = RectF(
                    rect.top * scaleX,
                    rect.left * scaleY,
                    rect.bottom * scaleX,
                    rect.right * scaleY
                )


                val alreadyExists = detectedProducts.any { existing ->
                    RectF.intersects(existing, mappedRect)
                }

                if (!alreadyExists) {
                    detectedProducts.add(mappedRect)
                }
            }
        }
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(lock) {
            for (rect in detectedProducts) {

                canvas.drawRect(rect, paintBox)
                canvas.drawText("✓", rect.centerX(), rect.centerY() + 25f, paintTick)
            }
        }
    }
}