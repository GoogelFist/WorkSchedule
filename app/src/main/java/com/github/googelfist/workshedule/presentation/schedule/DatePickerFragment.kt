package com.github.googelfist.workshedule.presentation.schedule

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.github.googelfist.workshedule.R
import java.util.Calendar

class DatePickerFragment() :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    lateinit var onDateSetListener: (String) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireActivity(),
            R.style.Theme_WorkSchedule_ScheduleDatePickerDialog,
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = "$year-${month + ONE_VALUE}-$day"
        onDateSetListener.invoke(date)
    }

    companion object {
        private const val ONE_VALUE = 1

        const val TAG = "date picker tag"

        fun newInstance(): DatePickerFragment {
            return DatePickerFragment()
        }
    }
}