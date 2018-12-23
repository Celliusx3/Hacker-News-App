package com.app.cellstudio.hacker_news_app.interactor.model

import android.net.Uri
import android.os.Build
import android.text.Html
import com.app.cellstudio.hacker_news_app.data.entities.HackerNewsItem
import com.app.cellstudio.hacker_news_app.interactor.util.StringUtil
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil
import paperparcel.PaperParcel
import paperparcel.PaperParcelable


@PaperParcel
class HackerNewsItemModel: PaperParcelable {
    var id: Int ?= null
    var createdAtI: Long ?= null
    var type: String =""
    var author: String = ""
    var title: String = ""
    var url: String = ""
    var text: String = ""
    var points: Int = 0
    var children: List<HackerNewsItemModel> = ArrayList()
    var isCommentExpands: Boolean = true

    fun getDetails(): String {
        val sb = StringBuilder()

        if (author.isNotEmpty()) {
            sb.append("Posted by ").append(this.author)
        }

        if (createdAtI != null) {
            sb.append(" • ").append(TimeUtil.getTimeAgo( createdAtI!! * 1000))
        }

        if (url.isNotEmpty()) {
            val uri = Uri.parse(url)
            sb.append(" • ").append(uri.authority)
        }

        return sb.toString()
    }

    private fun getTotalComments(): Int {
        var totalComments  = 0

        this.children.forEach {
            if (it.type.equals(COMMENT, true)) {
                totalComments += 1
            }
            totalComments += it.getTotalComments()
        }

        return totalComments
    }

    private fun getChildrenComments(): Int {
       return this.children.size
    }

    fun getDisplayPoints(): String {
        return this.points.toString()
    }

    fun getDisplayTotalComments(): String {
        return this.getTotalComments().toString()
    }

    fun getDisplayChildrenComments(): String {
        return this.getChildrenComments().toString()
    }

    fun getDisplayComments(): CharSequence {
        var tempText = this.text
        if (this.text.isEmpty()) {
             tempText = "<i>This post has been deleted or removed.</i>"
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StringUtil.removeTraillingWhiteLines(Html.fromHtml(tempText, Html.FROM_HTML_MODE_LEGACY))
        } else {
            StringUtil.removeTraillingWhiteLines(Html.fromHtml(tempText))
        }
    }

    fun getDisplayLoadMoreComments(): Boolean {
        return children.isNotEmpty()
    }

    companion object Factory {
        @JvmField val CREATOR = PaperParcelHackerNewsItemModel.CREATOR

        @JvmStatic
        fun create(hackerNewsItem: HackerNewsItem): HackerNewsItemModel {
            val model = HackerNewsItemModel()
            model.id = hackerNewsItem.id
            model.createdAtI = hackerNewsItem.created_at_i
            model.type = hackerNewsItem.type ?: ""
            model.author = hackerNewsItem.author ?: ""
            model.title = hackerNewsItem.title ?: ""
            model.url = hackerNewsItem.url ?: ""
            model.text = hackerNewsItem.text ?: ""
            model.points = hackerNewsItem.points ?: 0

            val tempChildren = ArrayList<HackerNewsItemModel>()
            if (hackerNewsItem.children != null) {
                hackerNewsItem.children.mapTo(tempChildren) { HackerNewsItemModel.create(it) }
                model.children = tempChildren
            }
            return model
        }

        private const val COMMENT = "comment"
    }
}