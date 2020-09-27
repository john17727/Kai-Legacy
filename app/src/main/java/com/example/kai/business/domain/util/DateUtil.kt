package com.example.kai.business.domain.util

import android.util.Log
import java.time.*
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class DateUtil
@Inject
constructor(
    private val dateTimeFormatter: DateTimeFormatter
) {

    // date format: yyyy-MM-dd'T'HH:mm:ss'Z'
//    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun getPublishTimeSpan(dateTime: String): String {
        val now = ZonedDateTime.now()
        try {
            val datePublished = ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).withZoneSameInstant(ZoneId.systemDefault())
            val span = Duration.between(datePublished, now)
            val hours = span.toHours()
            val days = span.toDays()
            Log.d("TimeSpan", "getPublishTimeSpan: Date Published: $datePublished Now: $now")
            return if (hours < 24) {
                formatHoursString(hours)
            } else {
                formatDaysString(days)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TimeSpan", "getPublishTimeSpan: Exception: $e")
        }
        return ""
    }

    private fun formatHoursString(hours: Long): String {
        return when (val hoursAbs = abs(hours)) {
            0L -> "Now"
            1L -> "$hoursAbs hour ago"
            else -> "$hoursAbs hours ago"
        }
    }

    private fun formatDaysString(days: Long): String {
        return when (val daysAbs = abs(days)) {
            1L -> "$daysAbs day ago"
            else -> "$daysAbs days ago"
        }
    }
}