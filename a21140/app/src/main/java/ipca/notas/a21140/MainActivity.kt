package ipca.notas.a21140

import android.app.Activity
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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val curricilarUnits = arrayListOf<CurricularUnit>()

    val adapter = GradesAdapter()
    var getNewUC : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Valores Teste
        curricilarUnits.add(CurricularUnit("Aplicações Móveis", 3, 19.0))
        curricilarUnits.add(CurricularUnit("Gestão Empresarial", 3, 9.0))
        curricilarUnits.add(CurricularUnit("Matemática", 3, 16.0))

        //Exercicio 3 (Parte de adapter)
        val listViewGrades = findViewById<ListView>(R.id.listViewGrades)
        listViewGrades.adapter = adapter

        // Receiver -> Exercício 5/6
        getNewUC = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val name = it.data?.getStringExtra("name")
                val year = it.data?.getIntExtra("year", 0)
                val grade = it.data?.getDoubleExtra("grade", 0.0)

                curricilarUnits.add(CurricularUnit(name, year, grade))

                adapter.notifyDataSetChanged()
            }
        }
    }

    //Exercicio 5 e 6
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.action_add -> {
                getNewUC?.launch(Intent(this, AddUCActivity::class.java))
                return true
            }
            R.id.action_average-> {
                var acc = 0.0

                //Soma a moda antiga
               /* for (c in curricilarUnits){
                    acc += c.grade?:0.0
                }

                var result = acc/curricilarUnits.size*/

                val result = curricilarUnits.sumOf {
                    it.grade?:0.0
                } / curricilarUnits.size

                Toast.makeText(this, "Average: ${result}", Toast.LENGTH_LONG).show()

                return true
            }
        }
        return false
    }

    inner class GradesAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return curricilarUnits.size
        }

        override fun getItem(position: Int): Any {
            return curricilarUnits[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_grade, parent, false)

            val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
            val textViewGrade = rootView.findViewById<TextView>(R.id.textViewGrade)

            textViewName.text = curricilarUnits[position].name
            textViewGrade.text = curricilarUnits[position].grade.toString()

            //Exercicio 7
            if ((curricilarUnits[position].grade?:0.0) < 10.0){
                textViewGrade.setTextColor(Color.RED)
            }
            else{
                textViewGrade.setTextColor(Color.GREEN)
            }

            return rootView
        }
    }

}