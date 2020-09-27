package com.example.kai.framework.presentation.common

import android.graphics.Rect
import android.view.View
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = spacing
        outRect.left = spacing
        outRect.right = spacing
    }
}