package com.app.cellstudio.hacker_news_app.interactor.util

class TimeUtil {

    companion object {

        private val SECOND_MILLIS = 1000
        private val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private val DAY_MILLIS = 24 * HOUR_MILLIS
        private val MONTH_MILLIS = 12 * DAY_MILLIS

        fun getTimeAgo(time: Long): String? {
            val now = System.currentTimeMillis()
            val diff = now - time

            return when {
                diff < MINUTE_MILLIS -> "just now"
                diff < HOUR_MILLIS -> (diff / MINUTE_MILLIS).toString() + "m"
                diff < DAY_MILLIS -> (diff / HOUR_MILLIS).toString() + "h"
                diff < MONTH_MILLIS -> (diff / DAY_MILLIS).toString() + "mo"
                else -> (diff / MONTH_MILLIS).toString() + "y"
            }
        }
    }
}