package com.example.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Mostrar layout do activity_main.xml
        setContentView(R.layout.activity_main)

        //Variáveis de controlo dos elementos da activity main
        val title = findViewById<TextView>(R.id.Title);
        val plus = findViewById<Button>(R.id.add);
        val minus = findViewById<Button>(R.id.minus);
        val contadorText = findViewById<TextView>(R.id.Contador_Text);
        val contador = findViewById<TextView>(R.id.contador);

        val onButtonTouchSomar : ((View)->Unit)? = {
            contador += contador;
        }
    }
}