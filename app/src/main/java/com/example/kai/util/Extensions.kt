package com.example.kai.util

object Extensions {
    fun CharSequence?.toLowerCase(): CharSequence {
        var lowercaseString = ""
        this?.forEach { c: Char ->
            lowercaseString += c.toLowerCase()
        }
        return lowercaseString
    }
}