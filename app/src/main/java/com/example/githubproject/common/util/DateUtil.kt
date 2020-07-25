package com.example.githubproject.common.util

import timber.log.Timber
import java.lang.IllegalArgumentException
import java.lang.NullPointerException
import java.text.ParseException
import java.text.SimpleDateFormat

class DateUtil {
    companion object {
        fun convertDateStringFormat(date: String, originalFormat: String, newFormat: String): String? {
            return try {
                val dateFormat = SimpleDateFormat(originalFormat)
                val dateUpdateAt = dateFormat.parse(date)
                dateFormat.applyPattern(newFormat)
                dateFormat.format(dateUpdateAt)
            } catch (ex: ParseException) {
                Timber.e(ex)
                null
            } catch (ex: IllegalArgumentException) {
                Timber.e(ex)
                null
            } catch (ex: NullPointerException) {
                Timber.e(ex)
                null
            }
        }
    }
}