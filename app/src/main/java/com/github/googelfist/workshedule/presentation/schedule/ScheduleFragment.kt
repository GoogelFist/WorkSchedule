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

    private val bottomSheetFragment by lazy(LazyThreadSafetyMode.NONE) { BottomSheetFragment.getNewInstance() }

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

        setupAppBar()

        setupButtons()

        binding.buttonShowBottomSheet.setOnClickListener {
            bottomSheetFragment.show(childFragmentManager, BottomSheetFragment.TAG)
        }
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
    }

    private fun setupAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.first_work_date_menu_item -> {
                    val datePickerFragment = DatePickerFragment.newInstance()
                    datePickerFragment.show(childFragmentManager, DatePickerFragment.TAG)
                    datePickerFragment.onDateSetListener = { date ->
                        scheduleViewModel.onRefreshFirstWorkDate(date)
                    }
                    true
                }
                R.id.schedule_type_menu_item -> {
                    val schedulePickerDialog = ScheduleTypePickerDialog.newInstance()
                    schedulePickerDialog.show(childFragmentManager, ScheduleTypePickerDialog.TAG)
                    schedulePickerDialog.onScheduleTypeSetListener = { scheduleType ->
                        scheduleViewModel.onRefreshScheduleType(scheduleType)
                    }
                    true
                }
                else -> false
            }
        }
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

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}