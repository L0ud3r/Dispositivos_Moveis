package ipca.budget.databasetestsqlserver

import org.json.JSONObject

data class ClienteModel(
    val id_cliente : Int? = null,
    val id_ginasio : Int? = null,
    val id_plano_nutricional : Int? = null,
    val nome : String? = null,
    val mail : String? = null,
    val telemovel : Int? = null,
    val pass_salt : String? = null,
    val pass_hash : String? = null,
    val peso : Double? = null,
    val altura : Int? = null,
    val gordura : Double? = null,
    val foto_perfil : String?  = null,
    val estado : String? = null
){
    fun toJSON() : JSONObject {
        val jsonObj = JSONObject()

        jsonObj.put("id_cliente", id_cliente)
        jsonObj.put("id_ginasio", id_ginasio)
        jsonObj.put("id_plano_nutricional", id_plano_nutricional)
        jsonObj.put("nome", nome)
        jsonObj.put("mail", mail)
        jsonObj.put("telemovel", telemovel)
        jsonObj.put("pass_salt", pass_salt)
        jsonObj.put("pass_hash", pass_hash)
        jsonObj.put("peso", peso)
        jsonObj.put("altura", altura)
        jsonObj.put("gordura", gordura)
        jsonObj.put("foto_perfil", foto_perfil)
        jsonObj.put("estado", estado)


        return jsonObj
    }

    companion object{

        fun fromJSON(jsonObject: JSONObject) : ClienteModel {
            return ClienteModel(
                jsonObject.getInt("id_cliente"),
                jsonObject.getInt("id_ginasio"),
                jsonObject.optInt("id_plano_nutricional", -1),
                jsonObject.getString("nome"),
                jsonObject.getString("mail"),
                jsonObject.getInt("telemovel"),
                jsonObject.getString("pass_salt"),
                jsonObject.getString("pass_hash"),
                jsonObject.optDouble("peso", -1.0),
                jsonObject.optInt("altura", -1),
                jsonObject.optDouble("gordura", -1.0),
                jsonObject.getString("foto_perfil"),
                jsonObject.getString("estado")
            )
        }
    }
}