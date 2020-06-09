package com.nemochen.hometimer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.hometimer.databinding.CountdownElementBinding
import com.nemochen.hometimer.model.CountdownElement

class CountdownElementAdapter : ListAdapter<CountdownElement, RecyclerView.ViewHolder>(DiffCallback){

    companion object DiffCallback : DiffUtil.ItemCallback<CountdownElement>() {
        override fun areItemsTheSame(oldItem: CountdownElement, newItem: CountdownElement): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CountdownElement, newItem: CountdownElement): Boolean {
            return (oldItem.name == newItem.name) && (oldItem.endTime == newItem.endTime)
        }
    }

    class ViewHolder(var binding: CountdownElementBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(countdownElement: CountdownElement) {
            binding.countdownElement = countdownElement
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val countdownElementBinding = CountdownElementBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(countdownElementBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(getItem(position) as CountdownElement)
            }
        }
    }

    @BindingAdapter("countdownElements")
    fun bindRecyclerViewWithCountdownElementList(recyclerView: RecyclerView, elementList: List<CountdownElement>) {
        elementList.let {
            recyclerView.adapter?.apply {
                when (this) {
                    is CountdownElementAdapter -> submitList(it)
                }
            }
        }
    }

}