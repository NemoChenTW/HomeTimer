package com.nemochen.hometimer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.hometimer.databinding.MainFragmentBinding

class MainFragment : Fragment() {


    companion object {
        val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var countdownElementAdapter = CountdownElementAdapter()

    private val elementDetailViewModel: ElementDetailViewModel by activityViewModels()

    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = countdownElementAdapter

        itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                countdownElementAdapter.removeItem(binding.recyclerView, viewModel.itemList.value, viewHolder.layoutPosition)
            }
        }).apply {
            this.attachToRecyclerView(binding.recyclerView)
        }

        binding.btnCreate.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                ElementDialogFragment().show(parentFragmentManager, ElementDialogFragment.TAG)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // TODO: Use the ViewModel
        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            // TODO: update items in countdownElementAdapter
            list -> countdownElementAdapter.bindRecyclerViewWithCountdownElementList(binding.recyclerView, list)
        })
        viewModel.toastMessage.observe(viewLifecycleOwner,
            Observer {
                s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
            })

        elementDetailViewModel.changedElement.observe(viewLifecycleOwner, Observer {
            countdownElementAdapter.addItem(binding.recyclerView, viewModel.itemList.value, viewModel.itemList?.value?.size!!, it)
        })
    }

}
