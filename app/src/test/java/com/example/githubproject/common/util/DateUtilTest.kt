package com.example.githubproject.common.util

import org.junit.Test

class DateUtilTest {
    @Test
    fun testConvertDateStringFormat_happyPath() {
        // Happy case
        val rawDate = "2020-06-27T05:52:40Z"
        val rawDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val newDateFormat = "MMM dd, yyyy HH:mm:ss a"
        val expectedFormattedDate = "Jun 27, 2020 05:52:40 AM"

        val formattedDate = DateUtil.convertDateStringFormat(rawDate, rawDateFormat, newDateFormat)

        assert(expectedFormattedDate == formattedDate)
    }

    @Test
    fun testConvertDateStringFormat_badOriginalFormat() {
        // Case with bad originalFormat
        val rawDate = "2020-06-27T05:52:40Z"
        val rawDateFormat = "someBadDateFormat"
        val newDateFormat = "MMM dd, yyyy HH:mm:ss a"

        val formattedDate = DateUtil.convertDateStringFormat(rawDate, rawDateFormat, newDateFormat)

        assert(formattedDate == null)
    }

    @Test
    fun testConvertDateStringFormat_badNewFormat() {
        // Case with bad newFormat
        val rawDate = "2020-06-27T05:52:40Z"
        val rawDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val newDateFormat = "someBadDateFormat"

        val formattedDate = DateUtil.convertDateStringFormat(rawDate, rawDateFormat, newDateFormat)

        assert(formattedDate == null)
    }
}