package ipca.budget.testtwopreparation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.budget.testtwopreparation.views.HorizontalSwitch

class HorizontalSwitchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_switch)

        val switch = findViewById<HorizontalSwitch>(R.id.horizontalSwitch)

        switch.setOnStateChanged {
            if (it){
                findViewById<TextView>(R.id.textViewOnOff).text = "Ligado"
            }else{
                findViewById<TextView>(R.id.textViewOnOff).text = "Desligado"
            }
        }

        findViewById<Button>(R.id.Slider).setOnClickListener{
            startActivity(Intent(this@HorizontalSwitchActivity, VerticalSwitchActivity::class.java))
        }

        findViewById<Button>(R.id.SwitcherBtn).setOnClickListener{
            startActivity(Intent(this@HorizontalSwitchActivity, MainActivity::class.java))
        }
    }
}