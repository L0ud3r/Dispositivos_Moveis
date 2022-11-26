package ipca.notas.testecm

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Lista de Objetos
    val Plants_List = arrayListOf<PlantUnit>()
    val Fixed_Plant_Array = arrayListOf<PlantUnit>()

    val adapter = PlantsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fixed_Plant_Array.add(PlantUnit("Planta Teste", "Blyat", "Planta Assasina"))
        Fixed_Plant_Array.add(PlantUnit("Planta Teste2", "Blyat2", "Planta Assasina2"))
        Fixed_Plant_Array.add(PlantUnit("Planta Teste3", "Blyat3", "Planta Assasina3"))

        Plants_List.add(Fixed_Plant_Array[0])
        Plants_List.add(Fixed_Plant_Array[1])
        Plants_List.add(Fixed_Plant_Array[2])



        val listViewPlants = findViewById<ListView>(R.id.listViewPlants)
        listViewPlants.adapter = adapter

    }

    //Criacao do Adapter
    inner class PlantsAdapter: BaseAdapter(){
        override fun getCount(): Int {
            return Plants_List.size
        }

        override fun getItem(position: Int): Any {
            return Plants_List[position]
        }

        override fun getItemId(position: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_plant, parent, false)

            //Atribuir IDs das texts views a variaveis
            val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
            val textViewLatinName = rootView.findViewById<TextView>(R.id.textViewLatinName)

            //Associar à classe PlantUnit
            textViewName.text = Plants_List[position].name
            textViewLatinName.text = Plants_List[position].latin_name

            //Carregar numa das listview rows, muda de pagina,
            //passando informação para outra activity
            rootView.setOnClickListener{
                val intent = Intent(this@MainActivity, PlantDetail::class.java)
                intent.putExtra("name", Plants_List[position].name)
                intent.putExtra("latin_name", Plants_List[position].latin_name)
                intent.putExtra("description", Plants_List[position].description)
                startActivity(intent)
            }

            return rootView
        }
    }
}