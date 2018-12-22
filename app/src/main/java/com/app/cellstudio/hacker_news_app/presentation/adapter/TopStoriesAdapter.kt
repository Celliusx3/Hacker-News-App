package com.app.cellstudio.hacker_news_app.presentation.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.databinding.ListTopStoriesBinding
import com.app.cellstudio.hacker_news_app.databinding.ListTopStoriesEmptyBinding
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel
import io.reactivex.subjects.PublishSubject

class TopStoriesAdapter(private val topStories: MutableList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var topStoriesData: Array<HackerNewsItemModel?> = arrayOfNulls(topStories.size)
    private val selectedModel = PublishSubject.create<HackerNewsItemModel>()
    private var listener: Listener? = null

    class ViewHolder : RecyclerView.ViewHolder {

        lateinit var binding: ListTopStoriesBinding

        constructor(view: View) : super(view)

        constructor(binding: ListTopStoriesBinding) : this(binding.root) {
            this.binding = binding
        }
    }

    class EmptyViewHolder : RecyclerView.ViewHolder {

        lateinit var binding: ListTopStoriesEmptyBinding

        constructor(view: View) : super(view)

        constructor(binding: ListTopStoriesEmptyBinding) : this(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder?
        val layoutInflater = LayoutInflater.from(parent.context)

        viewHolder = when (viewType) {
            VIEW_TYPE_EMPTY -> {
                val v2 = layoutInflater.inflate(R.layout.list_top_stories_empty, parent, false)
                EmptyViewHolder(v2)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ListTopStoriesBinding>(layoutInflater, R.layout.list_top_stories, parent, false)
                ViewHolder(binding)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(baseHolder: RecyclerView.ViewHolder, position: Int) {
        if (baseHolder is EmptyViewHolder) {
            listener?.onFirstVisible(topStories[position], position)
        } else if (baseHolder is ViewHolder) {
            baseHolder.binding.modelId = "No. " + (position + 1).toString()
            baseHolder.binding.model = topStoriesData[position]
            baseHolder.binding.setListener {
                if (topStoriesData[position] != null) {
                    selectedModel.onNext(topStoriesData[position]!!)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return topStories.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (topStoriesData[position] == null) {
            VIEW_TYPE_EMPTY
        } else VIEW_TYPE_DEFAULT
    }

    fun getSelectedModel(): PublishSubject<HackerNewsItemModel> {
        return selectedModel
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun updateViewHolder(position: Int, hackerNewsItemModel: HackerNewsItemModel) {
        topStoriesData[position] = hackerNewsItemModel
        notifyItemChanged(position)
    }

    fun updateData(models: List<Int>) {
        this.topStoriesData = arrayOfNulls(models.size)
        this.topStories.clear()
        this.topStories.addAll(models)
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_DEFAULT = 1
    }

    interface Listener {
        fun onFirstVisible(id: Int, position: Int)
    }
}
