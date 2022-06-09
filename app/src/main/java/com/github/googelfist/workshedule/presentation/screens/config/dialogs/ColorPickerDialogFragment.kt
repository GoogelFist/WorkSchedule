package com.github.googelfist.workshedule.presentation.screens.config.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.github.googelfist.workshedule.R
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class ColorPickerDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val context = requireContext()

        val dialog = ColorPickerDialog.Builder(context)
            .setTitle(context.getString(R.string.color_picker_title))
            .setPreferenceName(context.getString(R.string.color_picker_preference_name))
            .setPositiveButton(
                context.getString(android.R.string.ok),
                ColorEnvelopeListener { envelope, _ ->
                    val hexColor = "#${envelope.hexCode}"
                    parentFragmentManager.setFragmentResult(
                        REQUEST_KEY,
                        bundleOf(KEY_COLOR_RESPONSE to hexColor)
                    )
                })
            .setNegativeButton(context.getString(android.R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(COLOR_PICKER_BOTTOM_SPACE)
            .create()

        return dialog
    }

    companion object {
        private const val COLOR_PICKER_BOTTOM_SPACE = 12

        private val TAG = ColorPickerDialog::class.java.simpleName

        private const val KEY_COLOR_RESPONSE = "KEY_COLOR_RESPONSE"

        private val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(manager: FragmentManager) {
            val dialogFragment = ColorPickerDialogFragment()
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (String) -> Unit
        ) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner) { _, result ->
                listener.invoke(result.getString(KEY_COLOR_RESPONSE).toString())
            }
        }
    }
}