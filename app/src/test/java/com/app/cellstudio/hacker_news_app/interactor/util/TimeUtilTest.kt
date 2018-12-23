package com.app.cellstudio.hacker_news_app.interactor.util

import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.DAY_MILLIS
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.HOUR_MILLIS
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.MINUTE_MILLIS
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.MONTH_MILLIS
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.SECOND_MILLIS
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil.Companion.YEAR_MILLIS
import org.junit.Assert
import org.junit.Test

class TimeUtilTest {
    /**
     * As User if is less than a minute return 'just now'
     */
    @Test
    fun getTimeAgo_returnJustNow() {

        val now = 1545533917804
        val before = now - 59 * SECOND_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "just now")
    }

    /**
     * As User if is more than or equal a minute and less than an hour return '${x}m'
     */
    @Test
    fun getTimeAgo_returnXm() {

        val now = 1545533917804
        val before = now - 5 * MINUTE_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "5m")
    }

    /**
     * As User if is more than or equal an hour and less than a day return '${x}h'
     */
    @Test
    fun getTimeAgo_returnXh() {

        val now = 1545533917804
        val before = now - 5 * HOUR_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "5h")
    }

    /**
     * As User if is more than or equal a day and less than a month return '${x}d'
     */
    @Test
    fun getTimeAgo_returnXd() {

        val now = 1545533917804
        val before = now - 5 * DAY_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "5d")
    }

    /**
     * As User if is more than or equal a month and less than a year return '${x}mo'
     */
    @Test
    fun getTimeAgo_returnXmo() {

        val now = 1545533917804
        val before = now - 5 * MONTH_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "5mo")
    }

    /**
     * As User if is more than or equal a year return '${x}y'
     */
    @Test
    fun getTimeAgo_returnXy() {

        val now = 1545533917804
        val before = now - 5 * YEAR_MILLIS

        Assert.assertEquals(TimeUtil.getTimeAgo(now, before), "5y")
    }
}