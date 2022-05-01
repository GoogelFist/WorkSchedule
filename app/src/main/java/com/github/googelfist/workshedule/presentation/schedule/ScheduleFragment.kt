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
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
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

    private val bottomSheetFragment by lazy(LazyThreadSafetyMode.NONE) {
        BottomSheetFragment.getNewInstance()
    }

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        scheduleViewModel.scheduleState.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.dayList)
            binding.dateTextView.text = it.formattedDate
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        dayListAdapter = DayListAdapter()
        recyclerView.adapter = dayListAdapter
        recyclerView.itemAnimator = null
    }

    private fun setupButtons() {
        binding.previousButton.setOnClickListener {
            scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedPreviousMonth)
        }
        binding.currentButton.setOnClickListener {
            scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedCurrentMonth)
        }
        binding.nextButton.setOnClickListener {
            scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedNextMonth)
        }
        binding.buttonShowBottomSheet.setOnClickListener {
            bottomSheetFragment.show(parentFragmentManager, BottomSheetFragment.TAG)
        }
    }

    companion object {

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}