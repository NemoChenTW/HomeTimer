package com.nemochen.hometimer.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nemochen.hometimer.databinding.DialogFragmentBinding
import com.nemochen.hometimer.util.TimeDisplayUtil
import java.util.*

class ElementDialogFragment : DialogFragment() {

    companion object {
        val TAG = "ElementDialogFragment"
        fun newInstance() = ElementDialogFragment()
    }

    private lateinit var binding: DialogFragmentBinding
    private lateinit var viewModel: ElementDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ElementDialogViewModel::class.java]
        binding.viewModel = viewModel


        viewModel.editDateTrigger.observe(viewLifecycleOwner, Observer {
            context?.apply {
                if (it != null) {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener {
                                _, year, month, dayOfMonth -> binding.date.text = TimeDisplayUtil.dataFormater.format(Calendar.getInstance().apply { set(year, month, dayOfMonth) }.time)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
                Calendar.YEAR
            }
        })
    }
}