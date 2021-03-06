package com.nemochen.hometimer.ui.main

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.hometimer.databinding.CountdownElementBinding
import com.nemochen.hometimer.model.CountdownElement
import com.nemochen.hometimer.util.TimeDisplayUtil

class CountdownElementAdapter : ListAdapter<CountdownElement, RecyclerView.ViewHolder>(DiffCallback){
    companion object DiffCallback : DiffUtil.ItemCallback<CountdownElement>() {
        override fun areItemsTheSame(oldItem: CountdownElement, newItem: CountdownElement): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CountdownElement, newItem: CountdownElement): Boolean {
            return (oldItem.name == newItem.name)
                    && (oldItem.endTime == newItem.endTime)
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
                holder.bind(getItem(position).apply {
                    if (this.countDownTimer == null) {
                        this.countDownTimer = object : CountDownTimer(this.endTime - System.currentTimeMillis(), 1000) {
                            override fun onFinish() {
                                countDownTimer = null
                            }

                            override fun onTick(millisUntilFinished: Long) {
                                displayTimeString = TimeDisplayUtil.getDisplayString(millisUntilFinished / 1000)
                                notifyItemChanged(position, "payload")
                            }
                        }.start()
                    }
                } as CountdownElement)
            }
        }
    }

    @BindingAdapter("countdownElements")
    fun bindRecyclerViewWithCountdownElementList(recyclerView: RecyclerView, elementList: MutableList<CountdownElement>) {
        elementList.let {
            recyclerView.adapter?.apply {
                when (this) {
                    is CountdownElementAdapter -> {
                        submitList(it)
                        notifyItemRangeChanged(0, it.size)
                    }
                }
            }
        }
    }

    fun removeItem(recyclerView: RecyclerView, list: MutableList<CountdownElement>?, position: Int) {
        list?.let {
            recyclerView.adapter?.apply {
                when (this) {
                    is CountdownElementAdapter -> {
                        it.removeAt(position).let { removedItem ->
                            removedItem.countDownTimer?.cancel()
                            removedItem.countDownTimer = null
                        }
                        submitList(it)
                        notifyItemRemoved(position)
                    }
                }
            }
        }
    }

    fun addItem(recyclerView: RecyclerView, list: MutableList<CountdownElement>?, position: Int, countdownElement: CountdownElement) {
        list?.let {
            recyclerView.adapter?.apply {
                when (this) {
                    is CountdownElementAdapter -> {
                        it.add(position, countdownElement)
                        notifyItemInserted(position)
                    }
                }
            }
        }
    }

}