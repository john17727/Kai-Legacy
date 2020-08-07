package com.example.kai.business.domain.util

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil
@Inject
constructor(){

    // date format: yyyy-MM-dd'T'HH:mm:ss'Z'
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun getPublishTimeSpan(dateTime: String): String {
        val now = LocalDateTime.now()
        val datePublished = LocalDateTime.parse(dateTime, dateTimeFormatter)
        val span = Duration.between(datePublished, now)
        val hours = span.toHours()
        val days = span.toDays()
        return if (hours < 24) {
            formatHoursString(hours)
        } else {
            formatDaysString(days)
        }
    }

    private fun formatHoursString(hours: Long): String {
        return when (hours) {
            0L -> "Now"
            1L -> "$hours hour ago"
            else -> "$hours hours ago"
        }
    }

    private fun formatDaysString(days: Long): String {
        return when (days) {
            1L -> "$days day ago"
            else -> "$days days ago"
        }
    }
}