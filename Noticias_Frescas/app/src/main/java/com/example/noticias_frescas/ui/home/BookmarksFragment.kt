package com.example.noticias_frescas.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.noticias_frescas.*
import com.example.noticias_frescas.databinding.FragmentGeneralBinding
import com.example.noticias_frescas.ui.toShort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarksFragment : Fragment() {

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

        lifecycleScope.launch(Dispatchers.IO){
            AppDatabase.getDatabase(requireContext())?.articleDao()?.getAll().let {
                lifecycleScope.launch(Dispatchers.IO){
                    articles = it as ArrayList<Article>
                    adapter.notifyDataSetChanged()
                }
            }
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
            textViewArticleTitle.text = article.title
            textViewArticleBody.text = article.content
            textViewArticleDate.text = article.publishedAt?.toShort()

            article.urlToImage?.let {
                Backend.fetchImage(lifecycleScope, it){ bitmap ->
                    imageViewArticle.setImageBitmap(bitmap)
                }
            }


            rowView.setOnClickListener {
                Log.d(MainActivity.TAG, "article:${article.title}")

                findNavController().navigate(
                    R.id.action_bookmarksFragment_to_articleWebDetailFragment,
                    Bundle().apply {
                        putString(ArticleWebDetailFragment.ARTICLE_JSON_STRING,article.toJSON().toString())
                    }
                )

                //val intent = Intent(requireContext(), ArticleWebDetailActivity::class.java)
                //intent.putExtra(EXTRA_ARTICLE, article.toJSON().toString())
                //startActivity(intent)
            }

            return rowView
        }

    }
}