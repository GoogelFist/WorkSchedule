package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.BottomSheetBinding
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class BottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    private var _binding: BottomSheetBinding? = null
    private val binding: BottomSheetBinding
        get() = _binding!!

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        setupButtons()
    }

    private fun setupButtons() {
        binding.firstWorkDateButton.setOnClickListener {
            val datePickerFragment = DatePickerFragment.newInstance()
            datePickerFragment.show(parentFragmentManager, DatePickerFragment.TAG)
            datePickerFragment.onDateSetListener = { date ->
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshFirstWorkDate(date))
            }
        }
        binding.scheduleTypeButton.setOnClickListener {
            val schedulePickerDialog = ScheduleTypePickerDialog.newInstance()
            schedulePickerDialog.show(parentFragmentManager, ScheduleTypePickerDialog.TAG)
            schedulePickerDialog.onScheduleTypeSetListener = { scheduleType ->
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshScheduleType(scheduleType))
            }
        }
    }

    private fun observeViewModel() {
        scheduleViewModel.scheduleTypeState.observe(viewLifecycleOwner) { scheduleTypeState ->
            with(binding) {
                when (scheduleTypeState) {
                    is ScheduleTypeState.TwoInTwo -> {
                        dividerLine.visibility = View.VISIBLE
                        firstWorkDateButton.visibility = View.VISIBLE
                        dateTextViewValue.visibility = View.VISIBLE

                        dateTextViewValue.text = scheduleTypeState.firstWorkDate.toString()
                        scheduleTypeTextViewValue.text = scheduleTypeState.type
                    }
                    is ScheduleTypeState.Default -> {
                        dividerLine.visibility = View.GONE
                        firstWorkDateButton.visibility = View.GONE
                        dateTextViewValue.visibility = View.GONE

                        scheduleTypeTextViewValue.text = scheduleTypeState.type
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"

        fun getNewInstance(): BottomSheetFragment {
            return BottomSheetFragment()
        }
    }
}