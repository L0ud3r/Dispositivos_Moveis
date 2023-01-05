package ipca.budget.testtwopreparation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import ipca.budget.testtwopreparation.views.VerticalSlider

class VerticalSliderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        val verticalSliderLeft = findViewById<VerticalSlider>(R.id.verticalSlider)
        val verticalSliderRight = findViewById<VerticalSlider>(R.id.verticalSlider2)
        val locker = findViewById<Switch>(R.id.locker)
        val valueInput = findViewById<EditText>(R.id.inputValue)

        valueInput.doOnTextChanged{ text, start, before, count ->
            if(!valueInput.text.isEmpty()) verticalSliderRight.percent = valueInput.text.toString().toFloat()
        }

        findViewById<Button>(R.id.buttonlight).setOnClickListener{
            startActivity(Intent(this@VerticalSliderActivity, LightBrightnessActivity::class.java))
        }

        findViewById<Button>(R.id.buttonSwitch).setOnClickListener{
            startActivity(Intent(this@VerticalSliderActivity, MainActivity::class.java))
        }

        findViewById<Button>(R.id.hSwitcherButton).setOnClickListener{
            startActivity(Intent(this@VerticalSliderActivity, HorizontalSwitchActivity::class.java))
        }

        locker.setOnCheckedChangeListener { compoundButton, b ->
            if (locker.isChecked) {
                verticalSliderLeft.percent = verticalSliderRight.percent
                findViewById<TextView>(R.id.sliderValue2).text = "${verticalSliderRight.percent.toInt()} %"
                findViewById<TextView>(R.id.sliderValue).text = "${verticalSliderRight.percent.toInt()} %"
            }
        }

        verticalSliderLeft.setOnValueChangeListener {value ->
            if(!locker.isChecked) findViewById<TextView>(R.id.sliderValue).text = "${value.toInt()} %"
            if (locker.isChecked) {
                verticalSliderLeft.percent = verticalSliderRight.percent
                findViewById<TextView>(R.id.sliderValue).text = "${verticalSliderRight.percent.toInt()} %"
            }
        }

        verticalSliderRight.setOnValueChangeListener {value ->
            if(!locker.isChecked) findViewById<TextView>(R.id.sliderValue2).text = "${value.toInt()} %"

            if (locker.isChecked) {
                verticalSliderRight.percent = verticalSliderLeft.percent
                findViewById<TextView>(R.id.sliderValue2).text = "${verticalSliderRight.percent.toInt()} %"
            }
        }
    }
}