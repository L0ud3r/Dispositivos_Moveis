package ipca.lite.testpreparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class EditClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_client)

        val buttonEditFinished = findViewById<Button>(R.id.buttonEditDone)

        buttonEditFinished.setOnClickListener{
            val intentEdit = Intent()

            intentEdit.putExtra("id", intent.getLongExtra("id", -2))
            intentEdit.putExtra("name", findViewById<EditText>(R.id.editClientName).text.toString())
            intentEdit.putExtra("age", findViewById<EditText>(R.id.editClientAge).text.toString().toInt())
            intentEdit.putExtra("height", findViewById<EditText>(R.id.editClientHeight).text.toString().toInt())
            intentEdit.putExtra("weight", findViewById<EditText>(R.id.editClientWeight).text.toString().toDouble())

            if(findViewById<CheckBox>(R.id.checkBoxPremiumEdit).isChecked()) intentEdit.putExtra("isPremium", true)
            else intentEdit.putExtra("isPremium", false)

            setResult(RESULT_OK, intentEdit)
            finish()
        }
    }
}