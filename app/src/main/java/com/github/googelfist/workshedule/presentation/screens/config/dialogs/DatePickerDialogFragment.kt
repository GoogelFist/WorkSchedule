package com.github.googelfist.workshedule.presentation.screens.config.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.github.googelfist.workshedule.R
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireActivity(),
            R.style.Theme_WorkSchedule_DatePickerDialog,
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = "$year-${month + ONE_VALUE}-$day"
        parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_DATE_RESPONSE to date))
    }

    companion object {
        private const val ONE_VALUE = 1

        private val TAG = DatePickerDialogFragment::class.java.simpleName

        private const val KEY_DATE_RESPONSE = "KEY_DATE_RESPONSE"

        private val REQUEST_KEY = "$TAG:defaultRequestKey"


        fun show(manager: FragmentManager) {
            val dialogFragment = DatePickerDialogFragment()
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (String) -> Unit
        ) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner) { _, result ->
                listener.invoke(result.getString(KEY_DATE_RESPONSE).toString())
            }
        }
    }
}