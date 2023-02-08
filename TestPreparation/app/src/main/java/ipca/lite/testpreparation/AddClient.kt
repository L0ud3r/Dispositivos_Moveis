package ipca.lite.testpreparation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class AddClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)

        var buttonCreate = findViewById<Button>(R.id.buttonCreate)

        buttonCreate.setOnClickListener{
            val intent = Intent()

            intent.putExtra("name", findViewById<EditText>(R.id.editTextNewName).text.toString())
            intent.putExtra("age", findViewById<EditText>(R.id.editTextNewAge).text.toString().toInt())
            intent.putExtra("height", findViewById<EditText>(R.id.editTextNewHeight).text.toString().toInt())
            intent.putExtra("weight", findViewById<EditText>(R.id.editTextNewWeight).text.toString().toDouble())

            if(findViewById<CheckBox>(R.id.checkBoxGender).isChecked()){
                intent.putExtra("gender", 'm')
            }
            else{
                intent.putExtra("gender", 'f')
            }

            if(findViewById<CheckBox>(R.id.checkBoxPremium).isChecked()){
                intent.putExtra("isPremium", true)
            }
            else{
                intent.putExtra("isPremium", false)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}