package com.github.googelfist.workshedule.presentation.screens.config.dialogs

import android.content.Context
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.github.googelfist.workshedule.R
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

object DialogHelper {
    fun showPickedColorDialog(context: Context, color: (String) -> Unit) {

        ColorPickerDialog.Builder(context)
            .setTitle(context.getString(R.string.color_picker_title))
            .setPreferenceName(context.getString(R.string.color_picker_preference_name))
            .setPositiveButton(
                context.getString(android.R.string.ok),
                ColorEnvelopeListener { envelope, _ ->
                    val hexColor = "#${envelope.hexCode}"
                    color(hexColor)
                })
            .setNegativeButton(context.getString(android.R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(COLOR_PICKER_BOTTOM_SPACE)
            .show()
    }

    fun showEditTextDialog(
        context: Context,
        currentText: String,
        dialogTitle: String,
        dialogMessage: String,
        title: (String) -> Unit
    ) {
        val inputEditTextField = EditText(context)
        inputEditTextField.setText(currentText)

        val layout = FrameLayout(context)
        layout.setPaddingRelative(
            DIALOG_LAYOUT_START_PADDING,
            DIALOG_LAYOUT_TOP_PADDING,
            DIALOG_LAYOUT_END_PADDING,
            DIALOG_LAYOUT_BOTTOM_PADDING
        )
        layout.addView(inputEditTextField)

        val dialog = AlertDialog.Builder(
            context,
            R.style.ThemeOverlay_MaterialComponents_Dialog_Alert
        ).setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setView(layout)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val editTextInput = inputEditTextField.text.toString()
                title(editTextInput)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
        dialog.show()
    }


    private const val COLOR_PICKER_BOTTOM_SPACE = 12

    private const val DIALOG_LAYOUT_START_PADDING = 45
    private const val DIALOG_LAYOUT_TOP_PADDING = 5
    private const val DIALOG_LAYOUT_END_PADDING = 45
    private const val DIALOG_LAYOUT_BOTTOM_PADDING = 45
}