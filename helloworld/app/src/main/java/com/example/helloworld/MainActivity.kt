package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /*
        //Criação texto com tamanho definido
        var textViewerHello = TextView(this)
        textViewerHello.text = "Hello World!"
        textViewerHello.textSize = 32.0f

        //Criação de um botão com texto
        var buttonTranslate = Button(this)
        buttonTranslate.text = "Translate"

        //Criação de um Layout
        var linearLayout = LinearLayout(this)
        linearLayout.addView(textViewerHello)
        linearLayout.addView(buttonTranslate)*/

        //Mostrar layout do activity_main.xml
        setContentView(R.layout.activity_main)

        //Variáveis que controlam os diferentes elementos do activity_main.xml
        val textViewHello = findViewById<TextView>(R.id.texto)
        val buttonTranslate = findViewById<Button>(R.id.button)
        val buttonTranslateEng = findViewById<Button>(R.id.buttoneng)

        //Ação de mexer no botão português
        val onButtonTouch : ((View)->Unit)? = {
            //val button = it as? Button
            //button?.text = "Traduzir"

            textViewHello.text = "Ola Mundo!"
        }

        //Ação de mexer no botão inglês
        val onButtonTouchEng : ((View)->Unit)? = {
            //val button = it as? Button
            //button?.text = "Traduzir"

            textViewHello.text = "Hello World!"
        }

        //Definição de que ao carregar no botão pretendido (usar variável acima),
        //chama função pretendida
        buttonTranslate.setOnClickListener(onButtonTouch)
        buttonTranslateEng.setOnClickListener(onButtonTouchEng)

        //setContentView(linearLayout)
    }
}