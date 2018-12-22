package com.app.cellstudio.hacker_news_app.presentation.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.app.cellstudio.hacker_news_app.R
import com.app.cellstudio.hacker_news_app.databinding.ListCommentsBinding
import com.app.cellstudio.hacker_news_app.interactor.model.HackerNewsItemModel


class CommentsAdapter(private val comments: MutableList<HackerNewsItemModel>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    class ViewHolder : RecyclerView.ViewHolder {

        lateinit var binding: ListCommentsBinding
        val tvCommentsComments: TextView
        val rvListComments: RecyclerView
        val llListComments: LinearLayout

        constructor(view: View) : super(view) {
            tvCommentsComments = view.findViewById(R.id.tvCommentsComments)
            rvListComments = view.findViewById(R.id.rvListComments)
            llListComments = view.findViewById(R.id.llListComments)
            tvCommentsComments.movementMethod = LinkMovementMethod.getInstance()
        }

        constructor(binding: ListCommentsBinding) : this(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListCommentsBinding>(layoutInflater, R.layout.list_comments, parent, false)
        val viewHolder = CommentsAdapter.ViewHolder(binding)
        val rv = viewHolder.rvListComments
        val layoutManager = LinearLayoutManager(rv.context, LinearLayoutManager.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = 3
        rv.layoutManager = layoutManager
        rv.setRecycledViewPool(recycledViewPool)
        return viewHolder
    }

    override fun onBindViewHolder(baseHolder: CommentsAdapter.ViewHolder, position: Int) {
        baseHolder.binding.model = comments[position]
        baseHolder.binding.setListener {
            val pos = baseHolder.adapterPosition
            if (pos >= 0) {
                comments[position].isCommentExpands = !comments[position].isCommentExpands
                notifyItemChanged(position)
            }
        }

        val rvListComments = baseHolder.rvListComments
        if (rvListComments.adapter == null) {
            rvListComments.adapter = CommentsAdapter(comments[position].children!!.toMutableList())
            rvListComments.isNestedScrollingEnabled = false
        } else {
            (rvListComments.adapter as CommentsAdapter).updateData(comments[position].children!!.toMutableList())
        }

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    private fun updateData(models: List<HackerNewsItemModel>) {
        this.comments.clear()
        this.comments.addAll(models)
        notifyDataSetChanged()
    }
}