package ipca.lite.testpreparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class ClientDetails : AppCompatActivity() {

    private val clientDetailed = Client(null,null,null,null,null,null,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_details)

        var buttonReturn = findViewById<Button>(R.id.buttonReturn)

        val id = intent.getLongExtra("id", -2)
        val name = intent.getStringExtra("name")
        val height = intent.getIntExtra("height", -1)
        val weight = intent.getDoubleExtra("weight", -1.0)
        val age = intent.getIntExtra("age", -1)
        val gender = intent.getCharExtra("gender", '?')
        val isPremium = intent.getBooleanExtra("premium", false)

        findViewById<TextView>(R.id.textViewDetailIDValue).text = id.toString()
        findViewById<TextView>(R.id.textViewDetailNameValue).text = name
        findViewById<TextView>(R.id.textViewDetailAgeValue).text = age.toString()
        findViewById<TextView>(R.id.textViewDetailHeightValue).text = ((height.toDouble()) / 100.0).toString() + " m"
        findViewById<TextView>(R.id.textViewDetailWeightValue).text = weight.toString() + " Kg"

        when(gender) {
            'm' -> findViewById<TextView>(R.id.textViewDetailGenderValue).text = "Masculino"
            'f' -> findViewById<TextView>(R.id.textViewDetailGenderValue).text = "Feminino"
            '?' -> findViewById<TextView>(R.id.textViewDetailGenderValue).text = "Unkown"
        }

        if(isPremium){
            findViewById<TextView>(R.id.textViewDetailPremiumValue).text = "Premium Account"
        }
        else if(!isPremium){
            findViewById<TextView>(R.id.textViewDetailPremiumValue).text = "Base Account"
        }

        buttonReturn.setOnClickListener{
            finish()
        }

        clientDetailed.id = id
        clientDetailed.name = name
        clientDetailed.age = age
        clientDetailed.height = height
        clientDetailed.weight = weight
        clientDetailed.gender = gender
        clientDetailed.isPremium = isPremium
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Informação do Cliente nº ${clientDetailed.id}\n\n" +
                    "Nome: ${clientDetailed.name}\n" +
                    "Idade: ${clientDetailed.age} anos\n" +
                    "Altura: ${((clientDetailed.height?:0).toDouble() / 100.0)} m\n" +
                    "Peso: ${clientDetailed.weight} Kg\n" +
                    "Género: ${if(clientDetailed.gender == 'm') "Masculino" else "Feminino"}\n" +
                    "Tipo de Conta: ${if (clientDetailed.isPremium?:false) "Premium Account" else "Base Account"}\n" +
                    "\n\n" +
                    "Dados do cliente partilhados com sucesso!")

            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }
}