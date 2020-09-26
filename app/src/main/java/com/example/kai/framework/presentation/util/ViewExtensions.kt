package com.example.kai.framework.presentation.util

import android.view.View
import android.widget.TextView

object ViewExtensions {

    fun View.enable() {
        this.visibility = View.VISIBLE
    }

    fun View.disable() {
        this.visibility = View.GONE
    }
}