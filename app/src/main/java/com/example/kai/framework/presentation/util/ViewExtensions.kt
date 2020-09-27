package com.example.kai.framework.presentation.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kai.R

object ViewExtensions {

    fun View.enable() {
        this.visibility = View.VISIBLE
    }

    fun View.disable() {
        this.visibility = View.GONE
    }

    fun ImageView.load(imageUrl: String) {
        Glide.with(this).load(imageUrl).into(this)
    }
}