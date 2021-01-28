package com.simpson.goodssearch.ui.mydata

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.simpson.goodssearch.R

/**
 * TODO: document your custom view class.
 */
class DetailInfoView : View {

    private var _titleString: String? = null // TODO: use a default from R.string...
    private var _infoColor: Int = Color.RED // TODO: use a default from R.color...
    private var _infoDimension: Float = 0f // TODO: use a default from R.dimen...

    private lateinit var textPaint: TextPaint
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    /**
     * The text to draw
     */
    var titleString: String?
        get() = _titleString
        set(value) {
            _titleString = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * The font color
     */
    var infoColor: Int
        get() = _infoColor
        set(value) {
            _infoColor = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this dimension is the font size.
     */
    var infoDimension: Float
        get() = _infoDimension
        set(value) {
            _infoDimension = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this drawable is drawn above the text.
     */
    var infoDrawable: Drawable? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.DetailInfoView, defStyle, 0
        )

        _titleString = a.getString(
            R.styleable.DetailInfoView_titleString
        )
        _infoColor = a.getColor(
            R.styleable.DetailInfoView_infoColor,
            infoColor
        )
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        _infoDimension = a.getDimension(
            R.styleable.DetailInfoView_infoDimension,
            infoDimension
        )

        if (a.hasValue(R.styleable.DetailInfoView_infoDrawable)) {
            infoDrawable = a.getDrawable(
                R.styleable.DetailInfoView_infoDrawable
            )
            infoDrawable?.callback = this
        }

        a.recycle()

        // Set up a default TextPaint object
        textPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        textPaint.let {
            it.textSize = infoDimension
            it.color = infoColor
            textWidth = it.measureText(titleString)
            textHeight = it.fontMetrics.bottom
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        titleString?.let {
            // Draw the text.
            canvas.drawText(
                it,
                paddingLeft + (contentWidth - textWidth) / 2,
                paddingTop + (contentHeight + textHeight) / 2,
                textPaint
            )
        }

        // Draw the example drawable on top of the text.
        infoDrawable?.let {
            it.setBounds(
                paddingLeft, paddingTop,
                paddingLeft + contentWidth, paddingTop + contentHeight
            )
            it.draw(canvas)
        }
    }
}