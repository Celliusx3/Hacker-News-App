package com.app.cellstudio.hacker_news_app.interactor.util

class StringUtil {

    companion object {

        fun removeTraillingWhiteLines(text: CharSequence): CharSequence {
            if (text.isEmpty())
                return ""

            var tempText = text
            while (tempText[tempText.length - 1] == '\n') {
                tempText = tempText.subSequence(0, tempText.length - 1)
            }
            return tempText

        }
    }
}