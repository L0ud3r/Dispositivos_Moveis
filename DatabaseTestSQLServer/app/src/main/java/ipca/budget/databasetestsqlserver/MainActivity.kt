package ipca.budget.databasetestsqlserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var listClientes = arrayListOf<ClienteModel>()
    val adapter = AdapterCliente()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listClientesView = findViewById<ListView>(R.id.listviewclientes)
        listClientesView.adapter = adapter

        findViewById<Button>(R.id.button).setOnClickListener{
            ClienteRequests.getAllClienteStor(lifecycleScope){
                listClientes = it
                adapter.notifyDataSetChanged()
            }
        }
    }

    inner class AdapterCliente : BaseAdapter(){
        override fun getCount(): Int {
            return listClientes.size
        }

        override fun getItem(p0: Int): Any {
            return listClientes[p0]
        }

        override fun getItemId(p0: Int): Long {
            return listClientes[p0].id_cliente?.toLong()?:-1L
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootview = layoutInflater.inflate(R.layout.row_cliente, p2, false)

            rootview.findViewById<TextView>(R.id.idClienteValue).text = listClientes[p0].id_cliente.toString()
            rootview.findViewById<TextView>(R.id.nomeClienteValue).text = listClientes[p0].nome

            return rootview
        }

    }
}