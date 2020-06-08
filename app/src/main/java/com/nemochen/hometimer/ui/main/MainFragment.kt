package com.nemochen.hometimer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nemochen.hometimer.databinding.MainFragmentBinding

class MainFragment : Fragment() {


    companion object {
        val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        // TODO: Use the ViewModel

        viewModel.displayString.observe(viewLifecycleOwner,
            Observer {
                s -> binding.message.text = s
            })
        viewModel.toastMessage.observe(viewLifecycleOwner,
            Observer {
                s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
            })
    }

}
