package com.example.noticias_frescas

import androidx.lifecycle.LiveData
import androidx.room.*
import org.json.JSONObject
import java.util.*

//Identificar Artigo como uma Entidade da Database
@Entity
class Article {

    //Attibutes da class Article
    var titulo   : String? = null;
    var conteudo   : String? = null;
    var dataPublicacao   : Date? = null;
    var urlToImage   : String? = null;
    @PrimaryKey
    var url   : String

    //Construtor
    constructor(titulo: String?, conteudo :String?, dataPublicacao :Date?,
                urlToImage :String?, url :String){
        this.titulo = titulo
        this.conteudo = conteudo
        this.dataPublicacao = dataPublicacao
        this.urlToImage = urlToImage
        this.url = url
    }

    fun toJSON() : JSONObject{
        //Criacao de um objeto JSON onde vai sser armazenado os dados do artigo
        var jsonObject = JSONObject()

        //Inserir no josn object a informacao
        jsonObject.put("titulo", titulo)
        jsonObject.put("conteudo", conteudo)
        jsonObject.put("dataPublicacao", dataPublicacao?.toServerFormat())
        jsonObject.put("urlToImage", urlToImage)
        jsonObject.put("url", url)

        //Retornar Objeto
        return jsonObject
    }

    companion object{

        //Função para recolher dados do Json para um Article
        fun fromJSON(jsonObject: JSONObject): Article{
            return Article(
                jsonObject.getString("titulo"),
                jsonObject.getString("conteudo"),
                jsonObject.getString("dataPublicacao").parsePubDate(),
                jsonObject.getString("urlToImage"),
                jsonObject.getString("url"),
            )
        }
    }
}

//Requests para Database
@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll() : LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE url = :url")
    fun getByUrl(url: String): LiveData<Article?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)

}