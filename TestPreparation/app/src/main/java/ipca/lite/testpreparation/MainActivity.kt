package ipca.lite.testpreparation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var clientsList = arrayListOf<Client>()
    var client_adapter = AdapterClient()
    var receiverNewClient : ActivityResultLauncher<Intent>? = null
    var receiverEditClient : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * id: Long?,
        name: String?,
        height: Int?,
        weight: Double?,
        age: Int?,
        gender: Char?,
        isPremium: Boolean?*/
        clientsList.add(Client(0,"Pedro",183,69.7,21,'m',true))
        clientsList.add(Client(1,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(2,"Andreia",165,52.7,19,'f',false))
        clientsList.add(Client(3,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(4,"Andreia",165,52.7,19,'f',false))
        clientsList.add(Client(5,"Pedro",183,69.7,21,'m',true))
        clientsList.add(Client(6,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(40,"Andreia",165,52.7,19,'f',false))
        clientsList.add(Client(7,"Pedro",183,69.7,21,'m',true))
        clientsList.add(Client(8,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(9,"Andreia",165,52.7,19,'f',false))
        clientsList.add(Client(10,"Pedro",183,69.7,21,'m',true))
        clientsList.add(Client(11,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(24,"Andreia",165,52.7,19,'f',false))
        clientsList.add(Client(12,"Pedro",183,69.7,21,'m',true))
        clientsList.add(Client(13,"Joao",182,64.7,24,'m',true))
        clientsList.add(Client(45,"Andreia",165,52.7,19,'f',false))

        val listViewClients = findViewById<ListView>(R.id.listViewClients)
        listViewClients.adapter = client_adapter

        receiverNewClient = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                var betterID : Long = -2

                for(client in clientsList){
                    if(client.id?:-2 > betterID){
                        betterID = client.id!!
                    }
                }

                Log.d("MainActivity", betterID.toString())

                val id = betterID + 1
                val name = it.data?.getStringExtra("name")
                val height = it.data?.getIntExtra("height", -1)
                val weight = it.data?.getDoubleExtra("weight", -1.0)
                val age = it.data?.getIntExtra("age", -1)
                val gender = it.data?.getCharExtra("gender", '?')
                val isPremium = it.data?.getBooleanExtra("isPremium", false)

                clientsList.add(Client(id,name,height, weight, age, gender, isPremium))
                client_adapter.notifyDataSetChanged()

                Toast.makeText(this@MainActivity,"Cliente criado com sucesso!", Toast.LENGTH_LONG).show()
            }
        }

        receiverEditClient = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val id = it.data?.getLongExtra("id", -2)
                val name = it.data?.getStringExtra("name")
                val height = it.data?.getIntExtra("height", -1)
                val weight = it.data?.getDoubleExtra("weight", -1.0)
                val age = it.data?.getIntExtra("age", -1)

                //Genero nao é coisa de mudar, acorda para a vida, sai do Matrix
                val gender = it.data?.getCharExtra("gender", '?')
                val isPremium = it.data?.getBooleanExtra("isPremium", false)

                //Mudar valores
                for(client in clientsList){
                    if(id == client.id) {
                        client.name = name
                        client.height = height
                        client.weight = weight
                        client.age = age
                        client.isPremium = isPremium
                    }
                }

                client_adapter.notifyDataSetChanged()

                Toast.makeText(this@MainActivity,"Cliente editado com sucesso!", Toast.LENGTH_LONG).show()
            }
        }

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonQuit = findViewById<Button>(R.id.buttonQuit)

        buttonQuit.setOnClickListener{
            finish()
        }

        buttonAdd.setOnClickListener {
            receiverNewClient?.launch(Intent(this@MainActivity, AddClient::class.java))
        }

    }

    inner class AdapterClient : BaseAdapter(){
        override fun getCount(): Int {
            return clientsList.size
        }

        override fun getItem(p0: Int): Any {
            return clientsList[p0]
        }

        override fun getItemId(p0: Int): Long {
            return clientsList[p0].id?:-1L
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_client,p2,false)

            val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
            val textViewID = rootView.findViewById<TextView>(R.id.textViewID)
            val buttonDelete = rootView.findViewById<Button>(R.id.buttonDelete)
            val editButton = rootView.findViewById<ImageView>(R.id.buttonEdit)

            textViewName.text = "- " + clientsList[p0].name
            textViewID.text = clientsList[p0].id.toString()

            buttonDelete.setOnClickListener{
                clientsList.remove(clientsList[p0])
                client_adapter.notifyDataSetChanged()
            }

            rootView.setOnClickListener{
                val intent = Intent(this@MainActivity, ClientDetails::class.java)

                intent.putExtra("id", clientsList[p0].id)
                intent.putExtra("name", clientsList[p0].name)
                intent.putExtra("age", clientsList[p0].age)
                intent.putExtra("height", clientsList[p0].height)
                intent.putExtra("weight", clientsList[p0].weight)
                intent.putExtra("gender", clientsList[p0].gender)
                intent.putExtra("premium", clientsList[p0].isPremium)

                startActivity(intent)
            }

            editButton.setOnClickListener {
                val intentEdit = Intent(Intent(this@MainActivity, EditClient::class.java))

                intentEdit.putExtra("id", clientsList[p0].id)
                intentEdit.putExtra("name", clientsList[p0].name)
                intentEdit.putExtra("age", clientsList[p0].age)
                intentEdit.putExtra("height", clientsList[p0].height)
                intentEdit.putExtra("weight", clientsList[p0].weight)
                intentEdit.putExtra("gender", clientsList[p0].gender)
                intentEdit.putExtra("premium", clientsList[p0].isPremium)

                receiverEditClient?.launch(intentEdit)
            }

            return rootView
        }

    }
}
