package com.example.noticias_frescas

import androidx.lifecycle.LiveData
import androidx.room.*
import org.json.JSONObject
import java.util.*

//Identificar Artigo como uma Entidade da Database
@Entity
class Article {

    //Attibutes da class Article
    var title   : String? = null;
    var content   : String? = null;
    var publishedAt   : Date? = null;
    var urlToImage   : String? = null;
    @PrimaryKey
    var url   : String

    //Construtor
    constructor(title: String?, content :String?, publishedAt :Date?,
                urlToImage :String?, url :String){
        this.title = title
        this.content = content
        this.publishedAt = publishedAt
        this.urlToImage = urlToImage
        this.url = url
    }

    fun toJSON() : JSONObject{
        //Criacao de um objeto JSON onde vai sser armazenado os dados do artigo
        var jsonObject = JSONObject()

        //Inserir no josn object a informacao
        jsonObject.put("title", title)
        jsonObject.put("content", content)
        jsonObject.put("publishedAt", publishedAt?.toServerFormat())
        jsonObject.put("urlToImage", urlToImage)
        jsonObject.put("url", url)

        //Retornar Objeto
        return jsonObject
    }

    companion object{

        //Função para recolher dados do Json para um Article
        fun fromJSON(jsonObject: JSONObject): Article{
            return Article(
                jsonObject.getString("title"),
                jsonObject.getString("content"),
                jsonObject.getString("publishedAt").parsePubDate(),
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