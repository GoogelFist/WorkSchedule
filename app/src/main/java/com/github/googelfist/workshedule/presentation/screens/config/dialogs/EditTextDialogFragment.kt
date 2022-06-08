package com.github.googelfist.workshedule.presentation.screens.config.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.github.googelfist.workshedule.databinding.EditTextDialogFragmentBinding

typealias CustomInputDialogListener = (requestKey: String, currentText: String) -> Unit

class EditTextDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val requestKey = requireArguments().getString(ARG_REQUEST_KEY).toString()

        val currentText = requireArguments().getString(KEY_CURRENT_TEXT).toString()
        val dialogTitle = requireArguments().getString(KEY_DIALOG_TITLE).toString()
        val hint = requireArguments().getString(KEY_EDIT_TEXT_HINT).toString()

        val binding = EditTextDialogFragmentBinding.inflate(layoutInflater)
        binding.editText.setText(currentText)
        binding.textInputLayout.hint = hint

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(dialogTitle)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(android.R.string.cancel, null)
            .create()

        dialog.setOnShowListener {
            binding.editText.requestFocus()
            showKeyboard(binding.editText)

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enteredText = binding.editText.text.toString()

                parentFragmentManager.setFragmentResult(
                    requestKey,
                    bundleOf(KEY_ENTERED_TEXT_RESPONSE to enteredText)
                )
                dismiss()
            }

            dialog.setOnDismissListener { hideKeyboard(binding.editText) }
        }

        return dialog
    }

    private fun showKeyboard(view: View) {
        view.post {
            getInputMethodManager(view).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun hideKeyboard(view: View) {
        getInputMethodManager(view).hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    private fun getInputMethodManager(view: View): InputMethodManager {
        val context = view.context
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    companion object {
        private val TAG = EditTextDialogFragment::class.java.simpleName

        private const val KEY_ENTERED_TEXT_RESPONSE = "KEY_ENTERED_TEXT_RESPONSE"

        private const val KEY_CURRENT_TEXT = "KEY_CURRENT_TEXT"
        private const val KEY_DIALOG_TITLE = "KEY_DIALOG_TITLE"
        private const val KEY_EDIT_TEXT_HINT = "KEY_EDIT_TEXT_HINT"

        private const val ARG_REQUEST_KEY = "ARG_REQUEST_KEY"


        private val DEFAULT_REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(
            manager: FragmentManager,
            currentText: String,
            dialogTitle: String,
            hint: String,
            requestKey: String
        ) {
            val dialogFragment = EditTextDialogFragment()
            dialogFragment.arguments = bundleOf(
                KEY_CURRENT_TEXT to currentText,
                KEY_DIALOG_TITLE to dialogTitle,
                KEY_EDIT_TEXT_HINT to hint,
                ARG_REQUEST_KEY to requestKey
            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            requestKey: String,
            listener: CustomInputDialogListener
        ) {
            manager.setFragmentResultListener(requestKey, lifecycleOwner) { key, result ->
                listener.invoke(key, result.getString(KEY_ENTERED_TEXT_RESPONSE).toString())
            }
        }
    }
}