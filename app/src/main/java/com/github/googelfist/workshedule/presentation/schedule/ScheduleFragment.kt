package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleFragmentBinding
import com.github.googelfist.workshedule.domain.ScheduleType
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
            binding.twoInTwoDateTextView.text = it.getFormattedDate()
        }

        // TODO: string resources
        scheduleViewModel.scheduleType.observe(viewLifecycleOwner) { scheduleType ->
            when (scheduleType) {
                is ScheduleType.TwoInTwo -> {
                    binding.datePickerDialog.visibility = View.VISIBLE
                    binding.dateTextView.visibility = View.VISIBLE
                    binding.dateTextView.text = scheduleType.firstWorkDate.toString()
                    binding.scheduleTypeTextView.text = "2 / 2"
                }
                is ScheduleType.Default -> {
                    binding.datePickerDialog.visibility = View.GONE
                    binding.dateTextView.visibility = View.GONE
                    binding.scheduleTypeTextView.text = "Default"
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.twoInTwoDayRecyclerView

        dayListAdapter = DayListAdapter()
        recyclerView.adapter = dayListAdapter
    }

    private fun setupButtons() {
        binding.twoInTwoPreviousButton.setOnClickListener {
            scheduleViewModel.onGeneratePreviousMonth()
        }
        binding.twoInTwoCurrentButton.setOnClickListener {
            scheduleViewModel.onGenerateCurrentMonth()
        }
        binding.twoInTwoNextButton.setOnClickListener {
            scheduleViewModel.onGenerateNextMonth()
        }
        binding.datePickerDialog.setOnClickListener {
            val datePickerFragment = DatePickerFragment(scheduleViewModel)
            datePickerFragment.show(requireActivity().supportFragmentManager, DATE_PICKER_TAG)
        }
        binding.scheduleTypes.setOnClickListener { v: View ->
            showMenu(v, R.menu.schedule_types_menu)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener {
            scheduleViewModel.onRefreshScheduleType(it.toString())
            true
        }

        popup.show()
    }

    companion object {
        private const val DATE_PICKER_TAG = "datePicker"

        fun getNewInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}