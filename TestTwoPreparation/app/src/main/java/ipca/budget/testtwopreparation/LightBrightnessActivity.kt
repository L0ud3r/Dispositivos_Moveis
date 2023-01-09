package ipca.budget.testtwopreparation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.budget.testtwopreparation.views.LightBrightnessView
import ipca.budget.testtwopreparation.views.TestView

class LightBrightnessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_brightness)

        val textViewValue = findViewById<TextView>(R.id.textViewValue)
        val lightBrightnessView = findViewById<LightBrightnessView>(R.id.lightBrightnessView)


        lightBrightnessView.setOnValueChange = {
            textViewValue.setText(it.toInt().toString())
        }

        val lotacao = 25.toFloat()
        val lotacaoMax = 50.toFloat()
        val percentagem = lotacao/lotacaoMax

        findViewById<TestView>(R.id.testView).setPercentagem(percentagem)

        findViewById<Button>(R.id.button).setOnClickListener{
            startActivity(Intent(this@LightBrightnessActivity, VerticalSliderActivity::class.java))
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            startActivity(Intent(this@LightBrightnessActivity, HorizontalSwitchActivity::class.java))
        }

        findViewById<Button>(R.id.button3).setOnClickListener{
            startActivity(Intent(this@LightBrightnessActivity, MainActivity::class.java))
        }
    }
}