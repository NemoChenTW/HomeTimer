package com.nemochen.hometimer.ui.main

import android.app.Dialog
import android.os.Bundle
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
            builder.setView(activity?.layoutInflater?.inflate(R.layout.dialog_fragment, null))

            return builder.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }
}