package com.github.googelfist.workshedule.presentation.screens.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleFragmentBinding
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.presentation.screens.configlist.ConfigsListFragment
import com.github.googelfist.workshedule.presentation.screens.schedule.models.ScheduleEvent
import com.github.googelfist.workshedule.presentation.screens.schedule.recycler.DayListAdapter
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

        setupRecyclerView()
        observeViewModel()
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
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
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_view_container,
                        ConfigsListFragment.newInstance()
                    )
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit()
            }
        }
    }

    companion object {

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}