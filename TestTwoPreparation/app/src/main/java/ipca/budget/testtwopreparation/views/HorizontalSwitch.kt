package ipca.budget.testtwopreparation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

class HorizontalSwitch : View  {
    //Construtores obrigatórios
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //Variáveis que sejam úteis a usar para a interação com a view
    var isOn = false
    //Variavel para armazenar o isOn de forma a ter acesso na Activity
    private var onStateChanged : ((Boolean) -> Unit)? = null

    //Metodo que tem acesso ao onStateChanged na Activity
    fun setOnStateChanged(onStateChanged : ((Boolean) -> Unit)){
        this.onStateChanged = onStateChanged
    }

    //Override para fazer a view fisicamente
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.BLACK

        canvas?.drawRect(Rect(0,0,width, height), paint)

        //Margin para o proximo paint a ser usado (botoes)
        val margin = 38

        //Quando estiver On e quando estiver Off
        if(!isOn){
            paint.color = Color.GRAY
            canvas?.drawRect(Rect(margin, margin,width - margin, height/2), paint)
            paint.color = Color.WHITE
            canvas?.drawRect(Rect(margin, height / 2,width - margin, height - margin), paint)
        }
        else{
            paint.color = Color.GRAY
            canvas?.drawRect(Rect(margin, height / 2,width - margin, height - margin), paint)
            paint.color = Color.WHITE
            canvas?.drawRect(Rect(margin, margin,width - margin, height/2), paint)
        }

        //Fazer os textos dos botoes
        paint.color = Color.BLACK
        paint.textSize = 80F
        //centar texto na horizontal
        paint.textAlign = Paint.Align.CENTER
        canvas?.drawText("Off", width / 2f, margin + paint.textSize, paint)
        canvas?.drawText("On", width / 2f, (height - (margin + paint.textSize)), paint)
    }

    //
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if(event.y > height / 2){
                    isOn = true
                    //Muda o onStateChanged que pode ser usado na activity
                    onStateChanged?.invoke(isOn)
                    //Atualiza a View
                    invalidate()

                    return true
                }
                else{
                    isOn = false
                    onStateChanged?.invoke(isOn)
                    invalidate()

                    return true
                }
            }
        }
        return false
    }
}