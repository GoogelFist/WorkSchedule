package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleFragmentBinding
import com.github.googelfist.workshedule.presentation.recycler.DayListAdapter
import java.time.LocalDate
import javax.inject.Inject

class ScheduleFragment : Fragment() {

    private var _binding: ScheduleFragmentBinding? = null
    private val binding: ScheduleFragmentBinding
        get() = _binding!!

    //    private val firstWorkDate = LocalDate.of(2022, 4, 16)
//    private var firstWorkDate = LocalDate.of(2022, 4, 17)
    lateinit var firstWorkDate: LocalDate

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    private val twoInTwoViewModel by activityViewModels<ScheduleViewModel> {
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

//        twoInTwoViewModel.onSaveFirstWorkDate("2022-4-17")

        observeViewModel()

        setupRecyclerView()

        setupButtons()

        twoInTwoViewModel.onGenerateCurrentMonth(firstWorkDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        twoInTwoViewModel.month.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.getDaysList())
            binding.twoInTwoDateTextView.text = it.getFormattedDate()
        }

        twoInTwoViewModel.firstWorkDate.observe(viewLifecycleOwner) {
            firstWorkDate = it
            Log.e("PARSE", "$firstWorkDate")
            twoInTwoViewModel.onGenerateCurrentMonth(firstWorkDate)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.twoInTwoDayRecyclerView

        dayListAdapter = DayListAdapter()
        recyclerView.adapter = dayListAdapter
    }

    private fun setupButtons() {
        binding.twoInTwoPreviousButton.setOnClickListener {
            twoInTwoViewModel.onGeneratePreviousMonth(firstWorkDate)
        }
        binding.twoInTwoCurrentButton.setOnClickListener {
            twoInTwoViewModel.onGenerateCurrentMonth(firstWorkDate)
        }
        binding.twoInTwoNextButton.setOnClickListener {
            twoInTwoViewModel.onGenerateNextMonth(firstWorkDate)
        }
        binding.datePickerDialog.setOnClickListener {
            val datePickerFragment = DatePickerFragment(twoInTwoViewModel)
            datePickerFragment.show(requireActivity().supportFragmentManager, DATE_PICKER_TAG)
        }
    }


    companion object {

        private const val DATE_PICKER_TAG = "datePicker"

        fun getNewInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}