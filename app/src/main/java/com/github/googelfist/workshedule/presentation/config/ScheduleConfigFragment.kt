package com.github.googelfist.workshedule.presentation.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleConfigFragmentBinding
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

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
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
        setOnEditColorButtonClickListener()
        setupButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        scheduleViewModel.firstWorkDate.observe(viewLifecycleOwner) { date ->
            binding.dateTextViewValue.text = date.toString()
        }
        scheduleViewModel.scheduleTypePattern.observe(viewLifecycleOwner) { pattern ->
            dayTypeListAdapter.submitList(pattern)
        }
    }

    private fun setOnEditColorButtonClickListener() {
        dayTypeListAdapter.onEditColorButtonClickListener = { button, position ->
            button.setOnClickListener {
                val dayType = dayTypeListAdapter.currentList[position]

                onPickedColor(requireActivity()) { color ->
                    val newDayType = dayType.copy(backgroundColor = color)
                    scheduleViewModel.obtainEvent(ScheduleEvent.EditDayType(position, newDayType))
                    dayTypeListAdapter.notifyItemChanged(position)
                    scheduleViewModel.obtainEvent(ScheduleEvent.RefreshSchedulePattern)
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

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewEditDayTypes

        dayTypeListAdapter = DayTypeListAdapter()
        recyclerView.adapter = dayTypeListAdapter
    }

    private fun setupButtons() {
        binding.firstWorkDateButton.setOnClickListener {
            val datePickerFragment = DatePickerFragment.newInstance()
            datePickerFragment.show(parentFragmentManager, DatePickerFragment.TAG)
            datePickerFragment.onDateSetListener = { date ->
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshFirstWorkDate(date))
            }
        }
    }

    companion object {
        private const val COLOR_PICKER_BOTTOM_SPACE = 12

        fun newInstance(): ScheduleConfigFragment {
            return ScheduleConfigFragment()
        }
    }
}