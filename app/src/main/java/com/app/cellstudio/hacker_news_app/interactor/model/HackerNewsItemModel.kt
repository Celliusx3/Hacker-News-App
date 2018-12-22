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
    var id: Int? = null
    var created_at: String? = ""
    var created_at_i: Long ?= null
    var type: String ?= ""
    var author: String ? = ""
    var title: String? = ""
    var url: String ? = ""
    var text: String ? = ""
    var points: Int ? = null
    var parent_id: Int ? = null
    var story_id: Int ? = null
    var children: List<HackerNewsItemModel> ? = null
    var isCommentExpands: Boolean = true

    fun getDetails(): String {
        val sb = StringBuilder()

        if (author != null) {
            sb.append("Posted by ").append(this.author)
        }

        if (created_at_i != null) {
            sb.append(" • ").append(TimeUtil.getTimeAgo( created_at_i!! * 1000))
        }

        if (url != null) {
            val uri = Uri.parse(url)
            sb.append(" • ").append(uri.authority)
        }

        return sb.toString()
    }

    fun getTotalComments(): Int {
        var totalComments: Int  = 0

        this.children?.forEach {
            if (it.type.equals(COMMENT, true)) {
                totalComments += 1
            }
            totalComments += it.getTotalComments()
        }

        return totalComments
    }

    fun getChildrenComments(): Int {
       return this.children!!.size
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

        if (this.text != null){
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                StringUtil.removeTraillingWhiteLines(Html.fromHtml(this.text, Html.FROM_HTML_MODE_LEGACY))
            } else {
                StringUtil.removeTraillingWhiteLines(Html.fromHtml(this.text))
            }
        }
        return ""
    }

    fun getDisplayLoadMoreComments(): Boolean {
        return children != null && children!!.isNotEmpty()
    }

    companion object Factory {
        @JvmField val CREATOR = PaperParcelHackerNewsItemModel.CREATOR

        @JvmStatic
        fun create(hackerNewsItem: HackerNewsItem): HackerNewsItemModel {
            val model = HackerNewsItemModel()
            model.id = hackerNewsItem.id
            model.created_at = hackerNewsItem.created_at
            model.created_at_i = hackerNewsItem.created_at_i
            model.type = hackerNewsItem.type
            model.author = hackerNewsItem.author
            model.title = hackerNewsItem.title
            model.url = hackerNewsItem.url
            model.text = hackerNewsItem.text
            model.points = hackerNewsItem.points
            model.parent_id = hackerNewsItem.parent_id
            model.story_id = hackerNewsItem.story_id

            val tempChildren = ArrayList<HackerNewsItemModel>()
            if (hackerNewsItem.children != null) {
                hackerNewsItem.children.mapTo(tempChildren) { HackerNewsItemModel.create(it) }
                model.children = tempChildren
            }
            return model
        }

        private val COMMENT = "comment"
    }
}