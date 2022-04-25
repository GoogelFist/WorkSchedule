package com.github.googelfist.workshedule.presentation.schedule

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.github.googelfist.workshedule.R

class ScheduleTypePickerDialog : DialogFragment() {

    lateinit var onScheduleTypeSetListener: (String) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val scheduleTypeItems = resources.getStringArray(R.array.schedule_types)

            val title = resources.getString(R.string.schedule_type_picker_dialog_title)

            val positiveButtonTitle =
                resources.getString(R.string.schedule_type_picker_dialog_positive_button)

            val negativeButtonTitle =
                resources.getString(R.string.schedule_type_picker_dialog_negative_button)

            var positionClicked = DEFAULT_POSITION

            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(title)
                .setSingleChoiceItems(scheduleTypeItems, DEFAULT_POSITION) { dialog, item ->
                    positionClicked = item
                }
                .setPositiveButton(positiveButtonTitle) { dialog, id ->
                    onScheduleTypeSetListener(scheduleTypeItems[positionClicked])
                }
                .setNegativeButton(negativeButtonTitle) { dialog, id -> }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        private const val DEFAULT_POSITION = -1

        const val TAG = "Schedule Type Picker tag"

        fun newInstance(): ScheduleTypePickerDialog {
            return ScheduleTypePickerDialog()
        }
    }
}