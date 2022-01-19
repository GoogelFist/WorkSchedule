package com.github.googelfist.workschedule.presentation

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
import com.github.googelfist.workschedule.di.MainViewModelFactory
import com.github.googelfist.workschedule.presentation.recyclerview.DayListAdapter
import com.github.googelfist.workschedule.presentation.recyclerview.RecyclerViewSwipeListener

class MainActivityFragment : Fragment() {

    private var _binding: FragmentMainActivityBinding? = null
    private val binding: FragmentMainActivityBinding
        get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var dayListAdapter: DayListAdapter

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
        setupRecyclerView()

        viewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(requireContext())
        )[MainViewModel::class.java]

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

        dayListAdapter = DayListAdapter()
        rvDayList.adapter = dayListAdapter

        setRecyclerViewPool(rvDayList)

        rvDayList.onFlingListener = object : RecyclerViewSwipeListener() {
            override fun onSwipeUp() {
                viewModel.onGenerateNextMonth()
            }

            override fun onSwipeDown() {
                viewModel.onGeneratePreviousMonth()
            }
        }
        dayListAdapter.onDayClickListener = {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerViewPool(rvDayList: RecyclerView) {
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.ACTIVE_DAY_TYPE,
            DayListAdapter.ACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.INACTIVE_DAY_TYPE,
            DayListAdapter.INACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.TODAY_TYPE,
            DayListAdapter.TODAY_DAY_POOL_SIZE
        )
    }

    private fun setupButtons() {
        binding.ivMonthUp.setOnClickListener { viewModel.onGeneratePreviousMonth() }
        binding.ivMonthDown.setOnClickListener { viewModel.onGenerateNextMonth() }
        binding.ivCurrentMonth.setOnClickListener { viewModel.onGenerateCurrentMonth() }

        binding.ivSettings.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_container, SettingsFragment.newInstance())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    companion object {
        fun newInstance(): MainActivityFragment {
            return MainActivityFragment()
        }
    }
}
