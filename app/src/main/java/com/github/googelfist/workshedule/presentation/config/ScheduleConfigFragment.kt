package com.github.googelfist.workshedule.presentation.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleConfigFragmentBinding
import com.github.googelfist.workshedule.presentation.config.dialogs.DatePickerFragment
import com.github.googelfist.workshedule.presentation.config.dialogs.DialogHelper
import com.github.googelfist.workshedule.presentation.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.config.recycler.DayTypeListAdapter
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModel
import com.github.googelfist.workshedule.presentation.schedule.ScheduleViewModelFactory
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
import javax.inject.Inject

class ScheduleConfigFragment : Fragment() {

    private var _binding: ScheduleConfigFragmentBinding? = null
    private val binding: ScheduleConfigFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    @Inject
    lateinit var configViewModelFactory: ConfigViewModelFactory

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    private val configViewModel by activityViewModels<ConfigViewModel> {
        configViewModelFactory
    }

    lateinit var dayTypeListAdapter: DayTypeListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleConfigFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupButtons()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewEditDayTypes

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        dayTypeListAdapter = DayTypeListAdapter()
        recyclerView.adapter = dayTypeListAdapter
    }

    private fun observeViewModel() {
        configViewModel.firstWorkDate.observe(viewLifecycleOwner) { date ->
            binding.dateTextViewValue.text = date.toString()
        }
        configViewModel.scheduleTypePattern.observe(viewLifecycleOwner) { pattern ->
            dayTypeListAdapter.submitList(pattern)
        }
    }

    private fun setupButtons() {
        setOnChooseFirstWorkDateClickListener()
        setOnCreateDefaultTypeClickListener()
        setOnEditColorButtonClickListener()
        setOnEditTitleButtonClickListener()
        setOnDeleteButtonClickListener()
    }

    private fun setOnChooseFirstWorkDateClickListener() {
        binding.firstWorkDateButton.setOnClickListener {
            val datePickerFragment = DatePickerFragment.newInstance()
            datePickerFragment.show(parentFragmentManager, DatePickerFragment.TAG)
            datePickerFragment.onDateSetListener = { date ->
                configViewModel.obtainEvent(ConfigEvent.RefreshFirstWorkDate(date))
                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
            }
        }
    }

    private fun setOnCreateDefaultTypeClickListener() {
        binding.fButtonCreateType.setOnClickListener {

            configViewModel.obtainEvent(ConfigEvent.CreateDayType)
            configViewModel.obtainEvent(ConfigEvent.UpdateSchedulePattern)

            val lastPosition = dayTypeListAdapter.currentList.lastIndex
            dayTypeListAdapter.notifyItemInserted(lastPosition)

            scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
        }
    }

    private fun setOnEditColorButtonClickListener() {
        dayTypeListAdapter.onEditColorButtonClickListener = { position ->

            val dayType = dayTypeListAdapter.currentList[position]

            DialogHelper.showPickedColorDialog(requireActivity()) { color ->
                val newDayType = dayType.copy(backgroundColor = color)
                configViewModel.obtainEvent(ConfigEvent.EditDayType(position, newDayType))
                configViewModel.obtainEvent(ConfigEvent.UpdateSchedulePattern)

                dayTypeListAdapter.notifyItemChanged(position)

                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
            }
        }
    }

    private fun setOnEditTitleButtonClickListener() {

        dayTypeListAdapter.onEditTitleButtonClickListener = { position ->
            val dayType = dayTypeListAdapter.currentList[position]

            DialogHelper.showChangeTitleDialog(dayType.title, requireActivity()) { title ->
                val newDayType = dayType.copy(title = title)
                configViewModel.obtainEvent(ConfigEvent.EditDayType(position, newDayType))
                configViewModel.obtainEvent(ConfigEvent.UpdateSchedulePattern)

                dayTypeListAdapter.notifyItemChanged(position)

                scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
            }
        }
    }

    private fun setOnDeleteButtonClickListener() {
        dayTypeListAdapter.onDeleteButtonClickListener = { position ->
            configViewModel.obtainEvent(ConfigEvent.DeleteDayType(position))
            configViewModel.obtainEvent(ConfigEvent.UpdateSchedulePattern)

            dayTypeListAdapter.notifyItemRemoved(position)

            scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)
        }
    }

    companion object {
        fun newInstance(): ScheduleConfigFragment {
            return ScheduleConfigFragment()
        }
    }
}