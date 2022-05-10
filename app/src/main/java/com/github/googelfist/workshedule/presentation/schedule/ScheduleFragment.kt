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
import com.github.googelfist.workshedule.domain.models.ScheduleState
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

    // TODO:  splash screen
    private fun observeViewModel() {
        scheduleViewModel.scheduleState.observe(viewLifecycleOwner) { scheduleState ->
            when (scheduleState) {
                ScheduleState.LaunchingState -> {
                    with(binding) {
                        progressBar.visibility = View.VISIBLE
                        weekDaysLayout.visibility = View.GONE
                        navigationButtons.visibility = View.GONE
                        buttonShowBottomSheet.visibility = View.GONE
                    }
                }
                is ScheduleState.GeneratedState -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        weekDaysLayout.visibility = View.VISIBLE
                        navigationButtons.visibility = View.VISIBLE
                        buttonShowBottomSheet.visibility = View.VISIBLE
                    }
                    dayListAdapter.submitList(scheduleState.dayList)
                    binding.dateTextView.text = scheduleState.formattedDate
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        dayListAdapter = DayListAdapter()

        with(recyclerView) {
            adapter = dayListAdapter

            recycledViewPool.setMaxRecycledViews(
                DayListAdapter.DAY_TYPE,
                DayListAdapter.DAY_TYPE_POOL_SIZE
            )

            itemAnimator = null
        }
    }

    private fun setupButtons() {
        with(binding) {
            previousButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedPreviousMonth)
            }
            currentButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedCurrentMonth)
            }
            nextButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedNextMonth)
            }
            buttonShowBottomSheet.setOnClickListener {
                bottomSheetFragment.show(parentFragmentManager, BottomSheetFragment.TAG)
            }
        }
    }

    companion object {

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}