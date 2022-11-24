package ipca.notas.a21140

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

//Exercicio 4 (criar activity + fazer layout)
class AddUCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ucactivity)

        findViewById<Button>(R.id.addRow).setOnClickListener {
            val intent = Intent()

            intent.putExtra("name", findViewById<EditText>(R.id.editTextName).text.toString())
            intent.putExtra("year", findViewById<EditText>(R.id.editTextYear).text.toString().toInt())
            intent.putExtra("grade", findViewById<EditText>(R.id.editTextGrade).text.toString().toDouble())

            setResult(RESULT_OK, intent);
            finish()
        }
    }
}