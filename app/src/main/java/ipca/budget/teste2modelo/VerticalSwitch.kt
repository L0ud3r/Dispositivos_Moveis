package ipca.budget.teste2modelo
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.core.view.GestureDetectorCompat

class VerticalSwitch : View, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private var mDetector: GestureDetectorCompat = GestureDetectorCompat(context, this)
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    var isOn = true
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if(mDetector.onTouchEvent(event))
            true
        else
            super.onTouchEvent(event)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color =  Color.BLUE
        canvas?.drawRect(Rect(0,0,width, height), paint)
        val marginSwitch = 16
        if(isOn) {
            paint.color = Color.YELLOW
            canvas?.drawRect(
                Rect(
                    marginSwitch,
                    marginSwitch,
                    width - marginSwitch,
                    (height / 2) - marginSwitch
                ), paint
            )
        }
        else {
            paint.color =  Color.GREEN
            canvas?.drawRect(Rect(marginSwitch,(height/2) - marginSwitch, width - marginSwitch, height - marginSwitch), paint)
        }
        paint.color = Color.BLACK
        val textSize = 70F
        paint.textSize = textSize
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textAlign = Paint.Align.CENTER
        canvas?.drawText("ON", width/2F, textSize + marginSwitch, paint)
        canvas?.drawText("OFF", width/2F, height - (textSize + marginSwitch), paint)
    }
    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }
    override fun onShowPress(e: MotionEvent?) {
    }
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }
    override fun onLongPress(e: MotionEvent?) {
    }
    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val diffY = e2.y - e1.y
        isOn = diffY < 0
        postInvalidate()
        return true
    }
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        return true
    }
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }
}