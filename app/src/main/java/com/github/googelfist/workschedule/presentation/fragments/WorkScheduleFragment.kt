package com.github.googelfist.workschedule.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.databinding.FragmentMainActivityBinding
import com.github.googelfist.workschedule.di.TwoInTwoScheduleViewModelFactory
import com.github.googelfist.workschedule.presentation.MainViewModel
import com.github.googelfist.workschedule.presentation.recyclerview.RecyclerViewSwipeListener
import com.github.googelfist.workschedule.presentation.recyclerview.WorkDayListAdapter

class WorkScheduleFragment : Fragment() {

    private var _binding: FragmentMainActivityBinding? = null
    private val binding: FragmentMainActivityBinding
        get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var dayListAdapter: WorkDayListAdapter

    private var scheduleType: String = UNKNOWN_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        viewModel.dayListLD.observe(viewLifecycleOwner) { dayListAdapter.submitList(it) }
        viewModel.formatDateLD.observe(viewLifecycleOwner) { binding.tvYearMonth.text = it }
        setupRecyclerView()
        setupButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCHEDULE_TYPE)) {
            throw IllegalArgumentException("Schedule type is absent")
        }
        scheduleType = args.getString(SCHEDULE_TYPE).toString()
        if (scheduleType != TWO_IN_TWO) {
            throw IllegalArgumentException("Unknown schedule type $scheduleType")
        }
    }

    private fun initializeViewModel() {
        if (scheduleType == TWO_IN_TWO) {
            viewModel = ViewModelProvider(
                requireActivity(),
                TwoInTwoScheduleViewModelFactory(requireContext())
            )[MainViewModel::class.java]
        } else throw IllegalArgumentException("View model not initialize")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        dayListAdapter = WorkDayListAdapter()
        rvDayList.adapter = dayListAdapter

        setRecyclerViewPool(rvDayList)

        rvDayList.onFlingListener = object : RecyclerViewSwipeListener() {
            override fun onSwipeUp() {
                viewModel.onGeneratePreviousMonth()
            }

            override fun onSwipeDown() {
                viewModel.onGenerateNextMonth()
            }
        }
        dayListAdapter.onDayClickListener = {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerViewPool(rvDayList: RecyclerView) {
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.ACTIVE_WEEKEND_DAY_TYPE,
            WorkDayListAdapter.ACTIVE_WEEKEND_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.INACTIVE_WEEKEND_DAY_TYPE,
            WorkDayListAdapter.INACTIVE_WEEKEND_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.ACTIVE_WORK_DAY_TYPE,
            WorkDayListAdapter.ACTIVE_WORK_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.INACTIVE_WORK_DAY_TYPE,
            WorkDayListAdapter.INACTIVE_WORK_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.TODAY_WEEKEND_TYPE,
            WorkDayListAdapter.TODAY_WEEKEND_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            WorkDayListAdapter.TODAY_WORK_TYPE,
            WorkDayListAdapter.TODAY_WORK_DAY_POOL_SIZE
        )
    }

    private fun setupButtons() {
        binding.includeNavigationPanel.ivMonthUp.setOnClickListener { viewModel.onGeneratePreviousMonth() }
        binding.includeNavigationPanel.ivMonthDown.setOnClickListener { viewModel.onGenerateNextMonth() }
        binding.includeNavigationPanel.ivCurrentMonth.setOnClickListener { viewModel.onGenerateCurrentMonth() }

        binding.includeNavigationPanel.ivSettings.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_container, SettingsFragment.newInstance())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    companion object {
        private const val SCHEDULE_TYPE = "schedule type"
        private const val TWO_IN_TWO = "twoInTwo"
        private const val UNKNOWN_TYPE = "unknown type"

        fun newTwoInTwoFragment(): WorkScheduleFragment {
            return WorkScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(SCHEDULE_TYPE, TWO_IN_TWO)
                }
            }
        }
    }
}
