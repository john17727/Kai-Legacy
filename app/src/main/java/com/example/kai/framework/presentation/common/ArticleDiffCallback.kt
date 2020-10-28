package com.example.kai.framework.presentation.common

import androidx.recyclerview.widget.DiffUtil
import com.example.kai.business.domain.model.Article

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.source.name == newItem.source.name
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}