package ipca.teste.a21140

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //Variáveis que sejam úteis a usar para a interação com a view
    var var1 = false
    var var2 = false
    var var3 = false
    var var4 = false
    var total = 0

    //Variavel para armazenar o isOn de forma a ter acesso na Activity
    private var onStateChanged : ((Int) -> Unit)? = null

    //Metodo que tem acesso ao onStateChanged na Activity
    fun setOnStateChanged(onStateChanged : ((Int) -> Unit)){
        this.onStateChanged = onStateChanged
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK

        paint.strokeWidth = 40f
        canvas?.drawRect(Rect(0,0, width,height/4), paint)

        val radius = 125f
        val cy = height/8
        val cx1 = radius + 40f
        val cx2 = cx1 + 2 * radius
        val cx3 = cx2 + 2 * radius
        val cx4 = width - radius - 40f

        paint.strokeWidth = 5f
        canvas?.drawCircle(cx1, cy.toFloat(), radius, paint)
        canvas?.drawCircle(cx2, cy.toFloat(), radius, paint)
        canvas?.drawCircle(cx3, cy.toFloat(), radius, paint)
        canvas?.drawCircle(cx4, cy.toFloat(), radius, paint)

        if(var1){
            paint.style = Paint.Style.FILL
            canvas?.drawCircle(cx1, cy.toFloat(), radius, paint)
        }
        if(var2){
            paint.style = Paint.Style.FILL
            canvas?.drawCircle(cx2, cy.toFloat(), radius, paint)
        }
        if(var3){
            paint.style = Paint.Style.FILL
            canvas?.drawCircle(cx3, cy.toFloat(), radius, paint)
        }
        if(var4){
            paint.style = Paint.Style.FILL
            canvas?.drawCircle(cx4, cy.toFloat(), radius, paint)
        }
    }

    /*//Ao toque (para diferentes tipos de toque)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if(event.y > height / 2){

                    onStateChanged?.invoke(active)
                    invalidate()

                    return true
                }
                else{

                    onStateChanged?.invoke(active)
                    invalidate()

                    return true
                }
            }
        }
        return false
    }*/

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        if (event.action == MotionEvent.ACTION_DOWN) {
            val d1x = x - 125f + 40f
            val d2x = x - (125f + 40f + 2 * 125f)
            val d3x = x - (125f + 40f + 2 * 125f + 2 * 125f)
            val d4x = x - (width - 125f - 40f)
            val dy = y - height/8

            val distance1 = Math.sqrt(d1x * d1x + dy * dy.toDouble()).toFloat()
            val distance2 = Math.sqrt(d2x * d2x + dy * dy.toDouble()).toFloat()
            val distance3 = Math.sqrt(d3x * d3x + dy * dy.toDouble()).toFloat()
            val distance4 = Math.sqrt(d4x * d4x + dy * dy.toDouble()).toFloat()

            if (distance1 < 125f && !var1) {
                var1 = true
                total += 8

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance1 < 125f && var1) {
                var1 = false
                total -= 8

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance2 < 125f && !var2) {
                var2 = true
                total += 4

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance2 < 125f && var2) {
                var2 = false
                total -= 4

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance3 < 125f && !var3) {
                var3 = true
                total += 2

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance3 < 125f && var3) {
                var3 = false
                total -= 2

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance4 < 125f && !var4) {
                var4 = true
                total += 1

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }
            else if (distance4 < 125f && var4) {
                var4 = false
                total -= 1

                onStateChanged?.invoke(total)
                invalidate()
                return true
            }

        }
        return super.onTouchEvent(event)
    }

}