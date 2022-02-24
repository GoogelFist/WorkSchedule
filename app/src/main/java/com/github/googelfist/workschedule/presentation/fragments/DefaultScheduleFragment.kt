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
import com.github.googelfist.workschedule.di.defaultschedule.DefaultSchedule
import com.github.googelfist.workschedule.presentation.recyclerview.DefaultDayListAdapter
import com.github.googelfist.workschedule.presentation.viewmodel.ScheduleViewModel
import com.github.googelfist.workschedule.presentation.viewmodel.factory.DefaultScheduleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DefaultScheduleFragment : Fragment() {

    private var _binding: ScheduleActivityFragmentBinding? = null
    private val binding: ScheduleActivityFragmentBinding
        get() = _binding!!

    private var fragmentMonth = CURRENT_MONTH

    private val component by lazy {
        LazyThreadSafetyMode.NONE
        DaggerDefaultComponent.builder().context(requireActivity().application).build()
    }

    @Inject
    @DefaultSchedule
    lateinit var defaultScheduleViewModelFactory: DefaultScheduleViewModelFactory

    private val viewModel: ScheduleViewModel by lazy {
        LazyThreadSafetyMode.NONE
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
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

        when (fragmentMonth) {
            PREVIOUS_MONTH -> viewModel.onGeneratePreviousMonth()
            CURRENT_MONTH -> viewModel.onGenerateCurrentMonth()
            NEXT_MONTH -> viewModel.onGenerateNextMonth()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseParams() {
        val args = requireArguments()
        fragmentMonth = args.getString(FRAGMENT_MONTH).toString()
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

        rvDayList.itemAnimator = null

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

    companion object {
        private const val FRAGMENT_MONTH = "fragment month"
        private const val CURRENT_MONTH = "current month"
        private const val NEXT_MONTH = "next month"
        private const val PREVIOUS_MONTH = "previous month"

        fun newInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment()
        }

        fun newCurrentMonthInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(FRAGMENT_MONTH, CURRENT_MONTH)
                }
            }
        }

        fun newPreviousMonthInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(FRAGMENT_MONTH, PREVIOUS_MONTH)
                }
            }
        }

        fun newNextMonthInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(FRAGMENT_MONTH, NEXT_MONTH)
                }
            }
        }
    }
}
