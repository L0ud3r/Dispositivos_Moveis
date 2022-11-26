package ipca.plantas.speedruna21140

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class PlantDetail : AppCompatActivity() {

    var plantSelected = PlantUnit(null, null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        //criar variaveis a mostrar
        val name = intent.getStringExtra("name")
        val latinName = intent.getStringExtra("name_latin")
        val description = intent.getStringExtra("description")

        //ler variaveis e mostrar
        findViewById<TextView>(R.id.textViewDetailName).text = name
        findViewById<TextView>(R.id.textViewDetailNameLatin).text = latinName
        findViewById<TextView>(R.id.textViewDetailDescription).text = description

        //Armazenar valores em variaveis
        plantSelected.name = name
        plantSelected.name_latin = latinName
        plantSelected.description = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Se carregar no botao share
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Plant name: ${plantSelected.name}\nPlant latin name: ${plantSelected.name_latin}\nPlant description:${plantSelected.description}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }

}