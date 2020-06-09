package com.nemochen.hometimer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.hometimer.databinding.CountdownElementBinding
import com.nemochen.hometimer.model.CountdownElement


class CountdownElementAdapter(private var items: List<CountdownElement>) : RecyclerView.Adapter<CountdownElementAdapter.ViewHolder>(){

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

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countdownElement = items[position]
        holder.bind(countdownElement)
    }

}