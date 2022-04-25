package com.github.googelfist.workshedule.presentation.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.BottomSheetBinding
import com.github.googelfist.workshedule.domain.ScheduleType
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

        scheduleViewModel.scheduleType.observe(viewLifecycleOwner) { scheduleType ->
            when (scheduleType) {
                is ScheduleType.TwoInTwo -> {
                    binding.firstWorkDateTitleTextView.visibility = View.VISIBLE
                    binding.dateTextViewValue.text = scheduleType.firstWorkDate.toString()
                    binding.scheduleTypeTextViewValue.text = scheduleType.type

                }
                is ScheduleType.Default -> {
                    binding.firstWorkDateTitleTextView.visibility = View.GONE
                    binding.dateTextViewValue.visibility = View.GONE
                    binding.scheduleTypeTextViewValue.text = scheduleType.type
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