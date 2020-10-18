package com.example.kai.framework.presentation.topheadlines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kai.R
import com.example.kai.business.domain.model.Article
import com.example.kai.framework.presentation.util.ViewExtensions.disable
import com.example.kai.framework.presentation.util.ViewExtensions.load
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleListAdapter(private val articleSelectedListener: ArticleSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view, articleSelectedListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> holder.bind(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Article>) {
//        val currentList = ArrayList<Article>()
//        currentList.addAll(differ.currentList.toList())
//        currentList.addAll(list)
        differ.submitList(list)
    }

    class ArticleViewHolder(itemView: View, private val articleSelectedListener: ArticleSelectedListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Article) = with(itemView) {
            articleImage.load(item.urlToImage)
            articleTitle.text = item.title
            if (item.source.name.isNotEmpty()) articleSource.text = item.source.name else articleSource.disable()
            articleTimeSpan.text = item.timeSincePublished
            if (item.description.isNotEmpty()) articleDescription.text = item.description else articleDescription.disable()

            setOnClickListener {
                articleSelectedListener.onArticleClick(item.url)
            }
        }
    }

    interface ArticleSelectedListener {
        fun onArticleClick(articleUrl: String)
    }
}