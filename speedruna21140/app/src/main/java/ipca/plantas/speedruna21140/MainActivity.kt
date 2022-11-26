package ipca.plantas.speedruna21140

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var plants = arrayListOf<PlantUnit>()

    var adapter = PlantAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plants.add(PlantUnit("t1", "t1", "t1"))
        plants.add(PlantUnit("t2","t2","t2"))
        plants.add(PlantUnit("t3", "t3", "t3"))

        var listViewPlants = findViewById<ListView>(R.id.listViewPlants)
        listViewPlants.adapter = adapter

    }

    inner class PlantAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return plants.size
        }

        override fun getItem(p0: Int): Any {
            return plants[p0]
        }

        override fun getItemId(p0: Int): Long {
            //Não possuí IDs neste objeto
            return 0L
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_plant, p2, false)

            val textViewName = rootView.findViewById<TextView>(R.id.textViewPlantName)
            val textViewGrade = rootView.findViewById<TextView>(R.id.textViewPlantNameLatin)

            textViewName.text = plants[p0].name
            textViewGrade.text = plants[p0].name_latin

            rootView.setOnClickListener{
                //Mudar de activity
                val intent = Intent(this@MainActivity, PlantDetail::class.java)

                //Parametros a levar ao mudar de pagina e respetivos nomes associados (names)
                intent.putExtra("name", plants[p0].name)
                intent.putExtra("name_latin", plants[p0].name_latin)
                intent.putExtra("description", plants[p0].description)

                startActivity(intent)
            }

            return rootView
        }

    }
}