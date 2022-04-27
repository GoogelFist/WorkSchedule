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
        scheduleViewModel.month.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.getDaysList())
            binding.dateTextView.text = it.getFormattedDate()
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
            scheduleViewModel.onGeneratePreviousMonth()
        }
        binding.currentButton.setOnClickListener {
            scheduleViewModel.onGenerateCurrentMonth()
        }
        binding.nextButton.setOnClickListener {
            scheduleViewModel.onGenerateNextMonth()
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