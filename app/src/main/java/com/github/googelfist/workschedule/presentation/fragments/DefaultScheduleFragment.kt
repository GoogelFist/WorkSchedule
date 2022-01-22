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
import com.github.googelfist.workschedule.databinding.ScheduleActivityFragmentBinding
import com.github.googelfist.workschedule.di.DefaultScheduleViewModelFactory
import com.github.googelfist.workschedule.presentation.ScheduleViewModel
import com.github.googelfist.workschedule.presentation.recyclerview.DefaultDayListAdapter
import com.github.googelfist.workschedule.presentation.recyclerview.RecyclerViewSwipeListener

class DefaultScheduleFragment : Fragment() {

    private var _binding: ScheduleActivityFragmentBinding? = null
    private val binding: ScheduleActivityFragmentBinding
        get() = _binding!!

    private lateinit var viewModel: ScheduleViewModel

    private lateinit var dayListAdapter: DefaultDayListAdapter

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

        viewModel = ViewModelProvider(
            requireActivity(),
            DefaultScheduleViewModelFactory()
        )[ScheduleViewModel::class.java]

        viewModel.dayListLD.observe(viewLifecycleOwner) { dayListAdapter.submitList(it) }
        viewModel.formatDateLD.observe(viewLifecycleOwner) { binding.tvYearMonth.text = it }

        setupButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        dayListAdapter = DefaultDayListAdapter()
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
            DefaultDayListAdapter.ACTIVE_DAY_TYPE,
            DefaultDayListAdapter.ACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DefaultDayListAdapter.INACTIVE_DAY_TYPE,
            DefaultDayListAdapter.INACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DefaultDayListAdapter.TODAY_TYPE,
            DefaultDayListAdapter.TODAY_DAY_POOL_SIZE
        )
    }

    private fun setupButtons() {
        binding.includeNavigationPanel.ivMonthUp.setOnClickListener { viewModel.onGeneratePreviousMonth() }
        binding.includeNavigationPanel.ivMonthDown.setOnClickListener { viewModel.onGenerateNextMonth() }
        binding.includeNavigationPanel.ivCurrentMonth.setOnClickListener { viewModel.onGenerateCurrentMonth() }

        binding.includeNavigationPanel.ivSettings.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.schedule_activity_container, PreferenceFragment.newInstance())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    companion object {
        fun newInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment()
        }
    }
}
