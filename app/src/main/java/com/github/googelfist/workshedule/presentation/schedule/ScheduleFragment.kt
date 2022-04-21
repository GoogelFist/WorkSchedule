package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleFragmentBinding
import com.github.googelfist.workshedule.presentation.recycler.DayListAdapter
import javax.inject.Inject

class ScheduleFragment : Fragment() {

    private var _binding: ScheduleFragmentBinding? = null
    private val binding: ScheduleFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    lateinit var dayListAdapter: DayListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        setupRecyclerView()

        setupButtons()

        // TODO: create ui for this
        scheduleViewModel.onSaveScheduleType(TWO_IN_TWO_SCHEDULE_TYPE)
//        scheduleViewModel.onSaveScheduleType(DEFAULT_SCHEDULE_TYPE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        scheduleViewModel.month.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.getDaysList())
            binding.twoInTwoDateTextView.text = it.getFormattedDate()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.twoInTwoDayRecyclerView

        dayListAdapter = DayListAdapter()
        recyclerView.adapter = dayListAdapter
    }

    private fun setupButtons() {
        binding.twoInTwoPreviousButton.setOnClickListener {
            scheduleViewModel.onGeneratePreviousMonth()
        }
        binding.twoInTwoCurrentButton.setOnClickListener {
            scheduleViewModel.onGenerateCurrentMonth()
        }
        binding.twoInTwoNextButton.setOnClickListener {
            scheduleViewModel.onGenerateNextMonth()
        }
        binding.datePickerDialog.setOnClickListener {
            val datePickerFragment = DatePickerFragment(scheduleViewModel)
            datePickerFragment.show(requireActivity().supportFragmentManager, DATE_PICKER_TAG)
        }
    }

    companion object {
        private const val DATE_PICKER_TAG = "datePicker"

        private const val TWO_IN_TWO_SCHEDULE_TYPE = "2/2"
        private const val DEFAULT_SCHEDULE_TYPE = "Default"

        fun getNewInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}