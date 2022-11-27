package ipca.notas.a21140

import android.app.Activity
import android.app.Instrumentation.ActivityResult
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
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var ucs_list = arrayListOf<UnidadeCurricular>()
    //Variavel que recebe os novos dados
    var receiver : ActivityResultLauncher<Intent>? = null

    private var adapter = UCAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ucs_list.add(UnidadeCurricular("Matematica", 1, 20.0))
        ucs_list.add(UnidadeCurricular("Processamento Linguagem", 1, 5.0))
        ucs_list.add(UnidadeCurricular("Arquitetura Computadores", 3, 15.0))

        val listViewUCs = findViewById<ListView>(R.id.listViewUCs)
        listViewUCs.adapter = adapter

        receiver = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val nome_new = it.data?.getStringExtra("nome")
                val ano_new = it.data?.getIntExtra("nota", 0)
                val nota_new = it.data?.getDoubleExtra("ano", 0.0)

                ucs_list.add(UnidadeCurricular(nome_new, ano_new, nota_new))
                adapter.notifyDataSetChanged()
            }
        }
    }

    inner class UCAdapter: BaseAdapter(){
        override fun getCount(): Int {
            return ucs_list.size
        }

        override fun getItem(p0: Int): Any {
            return ucs_list[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_uc,p2,false)

            val textViewNome = rootView.findViewById<TextView>(R.id.textViewNome)
            val textViewAno = rootView.findViewById<TextView>(R.id.textViewAno)
            val textViewNota = rootView.findViewById<TextView>(R.id.textViewNota)

            textViewNome.text = ucs_list[p0].nome
            textViewAno.text = "Ano: " + ucs_list[p0].ano.toString()

            if(ucs_list[p0].nota!! < 10.0){
                textViewNota.text = ucs_list[p0].nota.toString() + " [Reprovado]"
                textViewNota.setTextColor(Color.RED)
            }
            else{
                textViewNota.text = ucs_list[p0].nota.toString() + " [Aprovado]"
                textViewNota.setTextColor(Color.GREEN)
            }

            return rootView
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.action_add -> {
                receiver?.launch(Intent(this@MainActivity, AddUnidade::class.java))
                return true
            }

            R.id.action_average -> {
                var sum_notas = 0.0

                for(uc in ucs_list){
                    sum_notas += uc.nota?:0.0
                }

                var media = sum_notas / ucs_list.size

                Toast.makeText(this, "Media: ${media}", Toast.LENGTH_LONG).show()

                return true
            }
        }

        return false
    }
}