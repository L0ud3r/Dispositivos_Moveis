package ipca.budget.databasetestsqlserver

import android.content.ContentValues.TAG
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ClienteRequests {
    private val client = OkHttpClient()

    fun getClientebyID(scope: CoroutineScope, callback: (ClienteModel)->Unit){
        scope.launch(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://3e4e-149-90-178-195.eu.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val service = retrofit.create(ApiService::class.java)
            val response = service.GetClientebyID()

            if(response.isSuccessful){
                val result = response.body()!!.toJSON()
                val JSONObject = JSONObject(result.toString())
                val cliente = ClienteModel.fromJSON(JSONObject)

                scope.launch(Dispatchers.IO) { callback(cliente) }
            }
            else{
                val cliente = ClienteModel(-1,-1,-1,"","",-1,
                    "","",-1.0,1,-1.0,"","")

                scope.launch(Dispatchers.IO) { callback(cliente) }
            }
        }
    }

    //GET BY STOR

    fun getAllClienteStor(scope: CoroutineScope, callback: (ArrayList<ClienteModel>)->Unit){
        scope.launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url("https://9999-149-90-178-195.eu.ngrok.io/api/Cliente/")
                .get()
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val result = response.body!!.string()

                val jsonObject = JSONObject(result)
                if (jsonObject.getString("statusCode") == "200"){
                    var clientes = arrayListOf<ClienteModel>()

                    val ClientesJSONData = jsonObject.getJSONObject("data")
                    val ClientesJSONList = ClientesJSONData.getJSONArray("value")

                    for (i in 0 until ClientesJSONList.length()) {
                        val item = ClientesJSONList.getJSONObject(i)
                        val cliente = ClienteModel.fromJSON(item)
                        clientes.add(cliente)
                    }

                    scope.launch(Dispatchers.Main){
                        callback(clientes)
                    }
                }
            }
        }
    }
}