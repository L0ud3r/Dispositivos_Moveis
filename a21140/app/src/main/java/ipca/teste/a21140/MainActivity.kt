package ipca.teste.a21140

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valueInput = findViewById<EditText>(R.id.editTextNumber)
        val customview = findViewById<CustomView>(R.id.customView)
        val textview = findViewById<TextView>(R.id.textView)

        customview.setOnStateChanged{
            textview.text = it.toString()
        }

        valueInput.doOnTextChanged{ text, start, before, count ->
            if(!valueInput.text.isEmpty()){
                if(valueInput.text.toString().toInt() == 1){
                    customview.total = 1
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = false
                    customview.var3 = false
                    customview.var4 = true
                }
                else if(valueInput.text.toString().toInt() == 2){
                    customview.total = 2
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = false
                    customview.var3 = true
                    customview.var4 = false
                }
                else if(valueInput.text.toString().toInt() == 3){
                    customview.total = 3
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = false
                    customview.var3 = true
                    customview.var4 = true
                }
                else if(valueInput.text.toString().toInt() == 4){
                    customview.total = 4
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = true
                    customview.var3 = false
                    customview.var4 = false
                }
                else if(valueInput.text.toString().toInt() == 5){
                    customview.total = 5
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = true
                    customview.var3 = false
                    customview.var4 = true
                }
                else if(valueInput.text.toString().toInt() == 6){
                    customview.total = 6
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = true
                    customview.var3 = true
                    customview.var4 = false
                }
                else if(valueInput.text.toString().toInt() == 7){
                    customview.total = 7
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = true
                    customview.var3 = true
                    customview.var4 = true
                }
                else{
                    customview.total = 0
                    textview.text = customview.total.toString()
                    customview.var1 = false
                    customview.var2 = false
                    customview.var3 = false
                    customview.var4 = false
                    Toast.makeText(this@MainActivity, "Insira um valor entre 0 e 7!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}