package com.app.cellstudio.hacker_news_app.interactor.util

import com.app.cellstudio.hacker_news_app.interactor.util.StringUtil.Companion.removeTraillingWhiteLines
import org.junit.Assert
import org.junit.Test

class StringUtilTest {
    /**
     * As User if text is empty return empty
     */
    @Test
    fun removeTraillingWhiteLines_returnEmptyIfEmpty() {

        val tempText = ""

        Assert.assertEquals(removeTraillingWhiteLines(tempText), "")
    }

    /**
     * As User if text has empty trail remove it
     */
    @Test
    fun removeTraillingWhiteLines_returnRemovedEmptyTrails() {

        val tempText = "Youtube has gotten out of control regarding copyright take-downs. " +
                "Something as inconspicuous as a MIDI file or some really old video game music in the background can get a take-down notice. " +
                "A publisher I used to follow, 10k subs, got taken down recently for this. Appeal process was useless for him. " +
                "I suspect a someone will submit tons of flags and then YouTube will automatically suspend the account. " +
                "Guilty until proven innocent in the eyes of YouTube.\n\n"
        val removedTrailsString = "Youtube has gotten out of control regarding copyright take-downs. " +
                "Something as inconspicuous as a MIDI file or some really old video game music in the background can get a take-down notice. " +
                "A publisher I used to follow, 10k subs, got taken down recently for this. Appeal process was useless for him. " +
                "I suspect a someone will submit tons of flags and then YouTube will automatically suspend the account. " +
                "Guilty until proven innocent in the eyes of YouTube."
        Assert.assertEquals(removeTraillingWhiteLines(tempText), removedTrailsString)
    }


    /**
     * As User if text does not have empty trail, return same string
     */
    @Test
    fun removeTraillingWhiteLines_returnStringWithoutEmptyTrail() {
        val tempText = "Youtube has gotten out of control regarding copyright take-downs. " +
                "Something as inconspicuous as a MIDI file or some really old video game music in the background can get a take-down notice. " +
                "A publisher I used to follow, 10k subs, got taken down recently for this. Appeal process was useless for him. " +
                "I suspect a someone will submit tons of flags and then YouTube will automatically suspend the account. " +
                "Guilty until proven innocent in the eyes of YouTube."
        val removedTrailsString = "Youtube has gotten out of control regarding copyright take-downs. " +
                "Something as inconspicuous as a MIDI file or some really old video game music in the background can get a take-down notice. " +
                "A publisher I used to follow, 10k subs, got taken down recently for this. Appeal process was useless for him. " +
                "I suspect a someone will submit tons of flags and then YouTube will automatically suspend the account. " +
                "Guilty until proven innocent in the eyes of YouTube."
        Assert.assertEquals(removeTraillingWhiteLines(tempText), removedTrailsString)
    }
}