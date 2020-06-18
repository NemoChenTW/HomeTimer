package com.nemochen.hometimer.ui.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nemochen.hometimer.databinding.DialogFragmentBinding
import com.nemochen.hometimer.model.CountdownElement
import com.nemochen.hometimer.util.TimeDisplayUtil
import java.util.*

class ElementDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "ElementDialogFragment"
    }

    private lateinit var binding: DialogFragmentBinding
    private lateinit var viewModel: ElementDialogViewModel
    private val elementDetailViewModel: ElementDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogFragmentBinding.inflate(inflater, container, false)

        binding.btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                elementDetailViewModel.modify(CountdownElement(binding.name.text.toString(), binding.viewModel?.getTimeInMillis()?.value!!))
                this@ElementDialogFragment.dismiss()
            }
        })

        binding.btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                this@ElementDialogFragment.dismiss()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ElementDialogViewModel::class.java]
        binding.viewModel = viewModel

        viewModel.getTimeInMillis().observe(viewLifecycleOwner, Observer {
            context?.apply {
                binding.date.text = TimeDisplayUtil.dataFormater.format(binding.viewModel?.calendar?.value?.time)
                binding.time.text = TimeDisplayUtil.timeFormaterWithoutSecond.format(binding.viewModel?.calendar?.value?.time)
            }
        })

        viewModel.editDateTrigger.observe(viewLifecycleOwner, Observer {
            context?.apply {
                it?.let {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener {
                                _, year, month, dayOfMonth -> binding.viewModel?.calendar?.value?.set(year, month, dayOfMonth)
                            binding?.viewModel?.updateTimeInMillis()
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }
        })

        viewModel.editTimeTrigger.observe(viewLifecycleOwner, Observer {
            context?.apply {
                it?.let {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(this,
                        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                            binding.viewModel?.calendar?.value?.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            binding.viewModel?.calendar?.value?.set(Calendar.MINUTE, minute)
                            binding.viewModel?.updateTimeInMillis()
                        },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true).show()
                }
            }
        })

        binding.viewModel?.initCalendar()
    }
}