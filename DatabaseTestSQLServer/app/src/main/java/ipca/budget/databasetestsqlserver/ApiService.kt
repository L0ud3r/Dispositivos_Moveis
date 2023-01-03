package ipca.budget.databasetestsqlserver

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("Cliente")
    suspend fun GetAllClientes(): Response<List<ClienteModel>>

    @GET("Cliente/1")
    suspend fun GetClientebyID(): Response<ClienteModel>
}