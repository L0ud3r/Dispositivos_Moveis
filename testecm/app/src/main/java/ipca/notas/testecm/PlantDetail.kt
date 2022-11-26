package ipca.notas.testecm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class PlantDetail : AppCompatActivity() {

    val plant = PlantUnit(null,null,null)

    //Buscar info da plant clicada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val name = intent.getStringExtra("name")
        val latinName = intent.getStringExtra("latin_name")
        val description = intent.getStringExtra("description")

        findViewById<TextView>(R.id.textViewNamePlantText).text = name
        findViewById<TextView>(R.id.textViewLatinNamePlantText).text = latinName
        findViewById<TextView>(R.id.textViewDescriptionText).text = description

        plant.name = name
        plant.latin_name = latinName
        plant.description = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Plant name: ${plant.name}\nPlant latin name: ${plant.latin_name}\nPlant description:${plant.description}")
            type = "text/plain"
        }

        //Add botao share a esta activity
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }
}