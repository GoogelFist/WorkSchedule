package com.github.googelfist.workshedule.presentation.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleConfigFragmentBinding
import com.github.googelfist.workshedule.presentation.config.recycler.DayTypeListAdapter
import com.github.googelfist.workshedule.presentation.schedule.DatePickerFragment
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModel
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModelFactory
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
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

    // TODO: create color picker dialog
    private fun setOnEditColorButtonClickListener() {
        dayTypeListAdapter.onEditColorButtonClickListener = { button, position ->
            button.setOnClickListener {
                val dayType = dayTypeListAdapter.currentList[position]
                val resultDayType = dayType.copy(backgroundColor = "#FF3700B3")

                scheduleViewModel.obtainEvent(ScheduleEvent.EditDayType(position, resultDayType))
                dayTypeListAdapter.notifyItemChanged(position)
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshSchedulePattern)
            }
        }
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewEditDayTypes

        dayTypeListAdapter = DayTypeListAdapter()

        with(recyclerView) {
            adapter = dayTypeListAdapter
        }
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

        fun newInstance(): ScheduleConfigFragment {
            return ScheduleConfigFragment()
        }
    }
}