package com.github.googelfist.workshedule.presentation.schedule

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import java.util.Calendar

class DatePickerFragment(private val viewModel: ViewModel) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    // TODO: not sure about this
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        if (viewModel is ScheduleViewModel) {
            viewModel.onSaveFirstWorkDate("$year-${month + ONE_VALUE}-$day")
            viewModel.onGenerateCurrentMonth()
        } else throw RuntimeException("View model is unknown")
    }

    companion object {
        private const val ONE_VALUE = 1
    }
}