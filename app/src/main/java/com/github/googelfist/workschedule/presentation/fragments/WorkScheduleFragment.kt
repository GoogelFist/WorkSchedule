package com.github.googelfist.workschedule.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.databinding.ScheduleActivityFragmentBinding
import com.github.googelfist.workschedule.di.workschedule.DaggerWorkComponent
import com.github.googelfist.workschedule.di.workschedule.WorkSchedule
import com.github.googelfist.workschedule.presentation.recyclerview.WorkDayListAdapter
import com.github.googelfist.workschedule.presentation.viewmodel.ScheduleViewModel
import com.github.googelfist.workschedule.presentation.viewmodel.factory.TwoInTwoScheduleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class WorkScheduleFragment : Fragment() {

    private var _binding: ScheduleActivityFragmentBinding? = null
    private val binding: ScheduleActivityFragmentBinding
        get() = _binding!!

    private val component by lazy {
        LazyThreadSafetyMode.NONE
        DaggerWorkComponent
            .builder()
            .context(requireActivity().application)
            .build()
    }

    private val viewModel: ScheduleViewModel by lazy {
        LazyThreadSafetyMode.NONE
        initializeViewModel()
    }

    @Inject
    @WorkSchedule
    lateinit var twoInTwoScheduleViewModelFactory: TwoInTwoScheduleViewModelFactory

    lateinit var dayListAdapter: WorkDayListAdapter

    private var scheduleType: String = UNKNOWN_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleActivityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.dayListLD.observe(viewLifecycleOwner) { dayListAdapter.submitList(it) }
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

    private fun initializeViewModel(): ScheduleViewModel {
        if (scheduleType == TWO_IN_TWO) {
            return ViewModelProvider(
                requireActivity(),
                twoInTwoScheduleViewModelFactory
            )[ScheduleViewModel::class.java]
        } else throw IllegalArgumentException("View model not initialize")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        dayListAdapter = WorkDayListAdapter()
        rvDayList.adapter = dayListAdapter

        val dividerItemDecoration = DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.recycler_divider)
        drawable?.let { dividerItemDecoration.setDrawable(it) }

        rvDayList.addItemDecoration(dividerItemDecoration)

        setRecyclerViewPool(rvDayList)

        rvDayList.itemAnimator = null

        dayListAdapter.onDayClickListener = {
            Snackbar.make(rvDayList, "$it", Snackbar.LENGTH_SHORT).show()
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
