package ipca.notas.a21140

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddUnidade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_unidade)

        findViewById<Button>(R.id.button_add).setOnClickListener {
            val intent = Intent(this@AddUnidade, MainActivity::class.java)

            intent.putExtra("nome", findViewById<EditText>(R.id.editTextNome).text.toString())
            intent.putExtra("nota", findViewById<EditText>(R.id.editTextNota).text.toString().toInt())
            intent.putExtra("ano", findViewById<EditText>(R.id.editTextAno).text.toString().toDouble())

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}