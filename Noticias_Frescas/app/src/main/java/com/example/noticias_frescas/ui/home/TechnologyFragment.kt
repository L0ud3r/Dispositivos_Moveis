package com.example.noticias_frescas.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noticias_frescas.*
import com.example.noticias_frescas.databinding.FragmentGeneralBinding

class TechnologyFragment : Fragment() {

    private var _binding: FragmentGeneralBinding? = null
    private val binding get() = _binding!!

    var articles = arrayListOf<Article>()
    val adapter = ArticlesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Backend.fetchTopHeadlines(lifecycleScope, "pt","technology"){
            articles = it
            adapter.notifyDataSetChanged()
        }


        binding.listViewArticles.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class ArticlesAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return articles.size
        }

        override fun getItem(positon: Int): Any {
            return articles[positon]
        }

        override fun getItemId(positon: Int): Long {
            return 0L
        }

        override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_article,parent, false)
            val textViewArticleTitle = rowView.findViewById<TextView>(R.id.textViewArticleTitle)
            val textViewArticleBody = rowView.findViewById<TextView>(R.id.textViewArticleBody)
            val textViewArticleDate = rowView.findViewById<TextView>(R.id.textViewArticleDate)
            val imageViewArticle = rowView.findViewById<ImageView>(R.id.imageViewArticle)

            val article = articles[position]
            textViewArticleTitle.text = article.titulo
            textViewArticleBody.text = article.conteudo
            textViewArticleDate.text = article.dataPublicacao?.toShort()

            article.urlToImage?.let {
                Backend.fetchImage(lifecycleScope, it){ bitmap ->
                    imageViewArticle.setImageBitmap(bitmap)
                }
            }


            rowView.setOnClickListener {
                Log.d(MainActivity.TAG, "article:${article.titulo}")

                findNavController().navigate(
                    R.id.action_tecnologyFragment_to_articleWebDetailFragment,
                    Bundle().apply {
                        putString(ArticleWebDetailFragment.ARTICLE_JSON_STRING,article.toJSON().toString())
                    }
                )

            }

            return rowView
        }
    }
}