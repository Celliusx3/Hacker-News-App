package com.app.cellstudio.hacker_news_app.interactor.util

class TimeUtil {

    companion object {

        const val SECOND_MILLIS: Long= 1000
        const val MINUTE_MILLIS: Long = 60 * SECOND_MILLIS
        const val HOUR_MILLIS: Long = 60 * MINUTE_MILLIS
        const val DAY_MILLIS: Long = 24 * HOUR_MILLIS
        const val MONTH_MILLIS: Long = 30 * DAY_MILLIS
        const val YEAR_MILLIS: Long = 365 * DAY_MILLIS

        fun getTimeAgo(now: Long, before: Long): String {
            val diff = now - before

            return when {
                diff < MINUTE_MILLIS -> "just now"
                diff < HOUR_MILLIS -> (diff / MINUTE_MILLIS).toString() + "m"
                diff < DAY_MILLIS -> (diff / HOUR_MILLIS).toString() + "h"
                diff < MONTH_MILLIS -> (diff / DAY_MILLIS).toString() + "d"
                diff < YEAR_MILLIS -> (diff / MONTH_MILLIS).toString() + "mo"
                else -> (diff / YEAR_MILLIS).toString() + "y"
            }
        }
    }
}