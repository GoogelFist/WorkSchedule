package com.github.googelfist.workshedule.presentation.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleConfigFragmentBinding
import com.github.googelfist.workshedule.presentation.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.config.recycler.DayTypeListAdapter
import com.github.googelfist.workshedule.presentation.schedule.DatePickerFragment
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModel
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModelFactory
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import javax.inject.Inject

class ScheduleConfigFragment : Fragment() {

    private var _binding: ScheduleConfigFragmentBinding? = null
    private val binding: ScheduleConfigFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    @Inject
    lateinit var configViewModelFactory: ConfigViewModelFactory

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    private val configViewModel by activityViewModels<ConfigViewModel> {
        configViewModelFactory
    }

    lateinit var dayTypeListAdapter: DayTypeListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleConfigFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewEditDayTypes

        dayTypeListAdapter = DayTypeListAdapter()
        recyclerView.adapter = dayTypeListAdapter
    }

    private fun observeViewModel() {
        configViewModel.firstWorkDate.observe(viewLifecycleOwner) { date ->
            binding.dateTextViewValue.text = date.toString()
        }
        configViewModel.scheduleTypePattern.observe(viewLifecycleOwner) { pattern ->
            dayTypeListAdapter.submitList(pattern)
        }
    }

    private fun setupButtons() {
        setOnChooseFirstWorkDateClickListener()
        setOnCreateDefaultTypeClickListener()
        setOnEditColorButtonClickListener()
        setOnEditTitleButtonClickListener()
        setOnDeleteButtonClickListener()
    }

    private fun setOnChooseFirstWorkDateClickListener() {
        binding.firstWorkDateButton.setOnClickListener {
            val datePickerFragment = DatePickerFragment.newInstance()
            datePickerFragment.show(parentFragmentManager, DatePickerFragment.TAG)
            datePickerFragment.onDateSetListener = { date ->
                configViewModel.obtainEvent(ConfigEvent.RefreshFirstWorkDate(date))
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
            }
        }
    }

    private fun setOnCreateDefaultTypeClickListener() {
        binding.buttonCreateType.setOnClickListener {
            configViewModel.obtainEvent(ConfigEvent.CreateDayType)

            val lastPosition = dayTypeListAdapter.itemCount - ONE_VAlUE
            dayTypeListAdapter.notifyItemInserted(lastPosition)

            configViewModel.obtainEvent(ConfigEvent.RefreshSchedulePattern)
            scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
        }
    }

    private fun setOnEditColorButtonClickListener() {
        dayTypeListAdapter.onEditColorButtonClickListener = { button, position ->
            button.setOnClickListener {
                val dayType = dayTypeListAdapter.currentList[position]

                onPickedColor(requireActivity()) { color ->
                    val newDayType = dayType.copy(backgroundColor = color)
                    configViewModel.obtainEvent(ConfigEvent.EditDayType(position, newDayType))
                    dayTypeListAdapter.notifyItemChanged(position)

                    configViewModel.obtainEvent(ConfigEvent.RefreshSchedulePattern)
                    scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
                }
            }
        }
    }

    private fun onPickedColor(context: Context, color: (String) -> Unit) {

        ColorPickerDialog.Builder(context)
            .setTitle(getString(R.string.color_picker_title))
            .setPreferenceName(getString(R.string.color_picker_preference_name))
            .setPositiveButton(
                getString(android.R.string.ok),
                ColorEnvelopeListener { envelope, _ ->
                    val hexColor = "#${envelope.hexCode}"
                    color(hexColor)
                })
            .setNegativeButton(getString(android.R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(COLOR_PICKER_BOTTOM_SPACE)
            .show()
    }

    private fun setOnEditTitleButtonClickListener() {

        dayTypeListAdapter.onEditTitleButtonClickListener = { button, position ->
            button.setOnClickListener {
                val dayType = dayTypeListAdapter.currentList[position]

                onTitleChanged(dayType.title, requireActivity()) { title ->
                    val newDayType = dayType.copy(title = title)
                    configViewModel.obtainEvent(ConfigEvent.EditDayType(position, newDayType))
                    dayTypeListAdapter.notifyItemChanged(position)

                    configViewModel.obtainEvent(ConfigEvent.RefreshSchedulePattern)
                    scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
                }
            }
        }
    }

    private fun onTitleChanged(currentText: String, context: Context, title: (String) -> Unit) {
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

        val dialog =
            AlertDialog.Builder(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                .setTitle(getString(R.string.edit_title_dialog_title))
                .setMessage(getString(R.string.edit_title_dialog_massage))
                .setView(layout)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    val editTextInput = inputEditTextField.text.toString()
                    title(editTextInput)
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
        dialog.show()
    }


    private fun setOnDeleteButtonClickListener() {
        dayTypeListAdapter.onDeleteButtonClickListener = { button, position ->
            button.setOnClickListener {
                configViewModel.obtainEvent(ConfigEvent.DeleteDayType(position))

                dayTypeListAdapter.notifyItemRemoved(position)

                configViewModel.obtainEvent(ConfigEvent.RefreshSchedulePattern)
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
            }
        }
    }

    companion object {
        private const val COLOR_PICKER_BOTTOM_SPACE = 12

        fun newInstance(): ScheduleConfigFragment {
            return ScheduleConfigFragment()
        }

        private const val ONE_VAlUE = 1

        private const val DIALOG_LAYOUT_START_PADDING = 45
        private const val DIALOG_LAYOUT_TOP_PADDING = 10
        private const val DIALOG_LAYOUT_END_PADDING = 45
        private const val DIALOG_LAYOUT_BOTTOM_PADDING = 45
    }
}