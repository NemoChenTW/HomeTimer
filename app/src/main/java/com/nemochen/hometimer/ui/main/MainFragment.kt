package com.nemochen.hometimer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemochen.hometimer.databinding.MainFragmentBinding
import com.nemochen.hometimer.model.CountdownElement

class MainFragment : Fragment() {


    companion object {
        val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var countdownElementAdapter = CountdownElementAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = countdownElementAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        // TODO: Use the ViewModel
        viewModel.itemList.observe(viewLifecycleOwner, Observer<List<CountdownElement>> {
            // TODO: update items in countdownElementAdapter
            list -> countdownElementAdapter.bindRecyclerViewWithCountdownElementList(binding.recyclerView, list)
        })
        viewModel.toastMessage.observe(viewLifecycleOwner,
            Observer {
                s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
            })
    }

}
