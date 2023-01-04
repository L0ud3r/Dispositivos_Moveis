package ipca.budget.testtwopreparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ipca.budget.testtwopreparation.views.VerticalSwitch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<VerticalSwitch>(R.id.verticalSwitch3).setOnStateChanged {
            if (it){
                findViewById<TextView>(R.id.textViewResult).text = "Ligado"
            }else{
                findViewById<TextView>(R.id.textViewResult).text = "Desligado"
            }
        }

        findViewById<Button>(R.id.hSwitchButton).setOnClickListener{
            startActivity(Intent(this@MainActivity, HorizontalSwitchActivity::class.java))
        }

        findViewById<Button>(R.id.buttonSlider).setOnClickListener{
            startActivity(Intent(this@MainActivity, VerticalSwitchActivity::class.java))
        }
    }
}