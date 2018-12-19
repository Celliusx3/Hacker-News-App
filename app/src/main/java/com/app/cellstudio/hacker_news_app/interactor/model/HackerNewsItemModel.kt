package com.app.cellstudio.hacker_news_app.interactor.model

import android.net.Uri
import com.app.cellstudio.hacker_news_app.data.entities.HackerNewsItem
import com.app.cellstudio.hacker_news_app.interactor.util.TimeUtil
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*


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

    fun getDetails(): String {
        val sb = StringBuilder()

        if (author != null) {
            sb.append("Posted by ").append(this.author)
        }

        if (created_at_i != null) {
            val now = System.currentTimeMillis()
            sb.append(" • ").append(TimeUtil.getTimeAgo( created_at_i!! * 1000))
//            sb.append(" • ").append(DateUtils.getRelativeTimeSpanString(
//                created_at_i!! * 1000, now,
//                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH).toString())
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

    fun getDisplayPoints(): String {
        return this.points.toString()
    }

    fun getDisplayComments(): String {
        return this.getTotalComments().toString()
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