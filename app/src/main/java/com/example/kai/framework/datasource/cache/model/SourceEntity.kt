package com.example.kai.framework.datasource.cache.model

import androidx.room.Entity

@Entity
data class SourceEntity(
    val id: String?,
    val name: String
) {
}