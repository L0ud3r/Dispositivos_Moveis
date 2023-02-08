package ipca.budget.a21140

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddOrcamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_orcamento)

        findViewById<Button>(R.id.buttonAddOrcamento).setOnClickListener {
            val intent = Intent()

            intent.putExtra("descricao", findViewById<EditText>(R.id.editTextAddDescricao).text.toString())
            intent.putExtra("valor", findViewById<EditText>(R.id.editTextValorNew).text.toString().toDouble())

            setResult(RESULT_OK, intent);
            finish()
        }
    }
}