package ipca.budget.a21140

import android.app.Activity
import android.content.Intent
import android.os.Build
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
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {

    var orcamentosList = arrayListOf<Orcamento>()
    var orcamentoAdapter = OrcamentoAdapter()
    var receiverNewData : ActivityResultLauncher<Intent>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orcamentosList.add(Orcamento("Orcamento 1", 6.99, Date(2015,1,1)))
        orcamentosList.add(Orcamento("Orcamento 2", 16.99, Date(2015,3,4)))
        orcamentosList.add(Orcamento("Orcamento 3", 56.99, Date(2015,2,1)))

        receiverNewData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val descricao = it.data?.getStringExtra("descricao")
                val valor = it.data?.getDoubleExtra("valor", 0.0)
                val dateNow = Date()

                orcamentosList.add(Orcamento(descricao, valor, dateNow))

                orcamentoAdapter.notifyDataSetChanged()
            }
        }

        var listViewOrcamento = findViewById<ListView>(R.id.listViewOrcamentos)
        listViewOrcamento.adapter = orcamentoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.action_add -> {
                receiverNewData?.launch(Intent(this@MainActivity, AddOrcamento::class.java))

                return true
            }

            R.id.action_sort-> {
                orcamentosList.sortBy{it.data}
                orcamentoAdapter.notifyDataSetChanged()

                return true
            }
            R.id.action_total-> {
                var total = 0.0

                for(orcamento in orcamentosList){
                    total += orcamento.valor!!
                }

                Toast.makeText(this,"Valor total de orcamento: ${total}", Toast.LENGTH_LONG).show()

                return true
            }
        }
        return false
    }

    inner class OrcamentoAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return orcamentosList.size
        }

        override fun getItem(p0: Int): Any {
            return orcamentosList[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_orcamento,p2,false)

            val textViewDescricao = rootView.findViewById<TextView>(R.id.textViewDescricao)
            val textViewValor = rootView.findViewById<TextView>(R.id.textViewValor)
            val textViewData = rootView.findViewById<TextView>(R.id.textViewData)

            textViewDescricao.text = orcamentosList[p0].descricao
            textViewValor.text = orcamentosList[p0].valor.toString() + " €"
            textViewData.text = orcamentosList[p0].data.toString()

            return rootView
        }

    }
}