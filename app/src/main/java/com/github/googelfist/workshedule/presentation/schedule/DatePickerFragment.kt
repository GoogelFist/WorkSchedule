package com.github.googelfist.workshedule.presentation.schedule

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment() :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    lateinit var dateSetListener: (String) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = "$year-${month + ONE_VALUE}-$day"
        dateSetListener.invoke(date)
    }

    companion object {
        private const val ONE_VALUE = 1
    }
}