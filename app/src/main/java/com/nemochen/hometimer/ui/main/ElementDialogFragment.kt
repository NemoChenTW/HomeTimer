package com.nemochen.hometimer.ui.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nemochen.hometimer.R

class ElementDialogFragment : DialogFragment() {

    companion object {
        val TAG = "ElementDialogFragment"
        fun newInstance() = ElementDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        context?.apply {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this, 0)
            val v = activity?.layoutInflater?.inflate(R.layout.dialog_fragment, null)
            v?.findViewById<TextView>(R.id.date)?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    DatePickerDialog(this@apply, object : DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            (v as TextView).text = "$year/$month/$dayOfMonth"
                        }

                    }, 2020, 6, 17).show()
                }

            })
            builder.setView(v)

            return builder.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }
}