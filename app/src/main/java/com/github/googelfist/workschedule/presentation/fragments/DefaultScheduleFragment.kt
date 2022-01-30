package com.github.googelfist.workschedule.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
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
import com.github.googelfist.workschedule.di.defaultschedule.DaggerDefaultComponent
import com.github.googelfist.workschedule.presentation.recyclerview.DefaultDayListAdapter
import com.github.googelfist.workschedule.presentation.recyclerview.RecyclerViewSwipeListener
import com.github.googelfist.workschedule.presentation.viewmodel.ScheduleViewModel
import com.github.googelfist.workschedule.presentation.viewmodel.factory.DefaultScheduleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DefaultScheduleFragment : Fragment() {

    private var _binding: ScheduleActivityFragmentBinding? = null
    private val binding: ScheduleActivityFragmentBinding
        get() = _binding!!

    private val component by lazy {
        DaggerDefaultComponent.builder().context(requireActivity().application).build()
    }

    @Inject
    lateinit var defaultScheduleViewModelFactory: DefaultScheduleViewModelFactory

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            defaultScheduleViewModelFactory
        )[ScheduleViewModel::class.java]
    }

    lateinit var dayListAdapter: DefaultDayListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
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

        val dividerItemDecoration = DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.recycler_divider)
        drawable?.let { dividerItemDecoration.setDrawable(it) }

        rvDayList.addItemDecoration(dividerItemDecoration)

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
            Snackbar.make(rvDayList, "$it", Snackbar.LENGTH_SHORT).show()
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

        binding.ivSettings.setOnClickListener {
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
