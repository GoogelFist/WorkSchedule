package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.TwoInTwoScheduleFragmentBinding
import com.github.googelfist.workshedule.presentation.schedule.recycler.ScheduleDayListAdapter
import javax.inject.Inject

class ScheduleFragment : Fragment() {

    private var _binding: TwoInTwoScheduleFragmentBinding? = null
    private val binding: TwoInTwoScheduleFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    private val twoInTwoViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    lateinit var scheduleDayListAdapter: ScheduleDayListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TwoInTwoScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        setupRecyclerView()

        setupButtons()
    }

    private fun setupButtons() {
        binding.twoInTwoPreviousButton.setOnClickListener {
            twoInTwoViewModel.onGeneratePreviousMonth()
        }
        binding.twoInTwoCurrentButton.setOnClickListener {
            twoInTwoViewModel.onGenerateCurrentMonth()
        }
        binding.twoInTwoNextButton.setOnClickListener {
            twoInTwoViewModel.onGenerateNextMonth()
        }
    }

    private fun observeViewModel() {
        twoInTwoViewModel.month.observe(viewLifecycleOwner) {
            scheduleDayListAdapter.submitList(it.getDaysList())
            binding.twoInTwoDateTextView.text = it.getFormattedDate()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.twoInTwoDayRecyclerView

        scheduleDayListAdapter = ScheduleDayListAdapter()
        recyclerView.adapter = scheduleDayListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun getNewInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}