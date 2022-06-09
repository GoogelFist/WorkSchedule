package com.github.googelfist.workshedule.presentation.screens.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleConfigFragmentBinding
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.presentation.screens.config.dialogs.ColorPickerDialogFragment
import com.github.googelfist.workshedule.presentation.screens.config.dialogs.DatePickerDialogFragment
import com.github.googelfist.workshedule.presentation.screens.config.dialogs.EditTextDialogFragment
import com.github.googelfist.workshedule.presentation.screens.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.screens.config.models.ConfigState
import com.github.googelfist.workshedule.presentation.screens.config.recycler.DayTypeListAdapter
import com.github.googelfist.workshedule.presentation.screens.schedule.ScheduleViewModel
import com.github.googelfist.workshedule.presentation.screens.schedule.ScheduleViewModelFactory
import com.github.googelfist.workshedule.presentation.screens.schedule.models.ScheduleEvent
import javax.inject.Inject
import kotlin.properties.Delegates

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

    lateinit var dayType: DayType
    var dayTypePosition by Delegates.notNull<Int>()

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewEditDayTypes.adapter = null
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
        configViewModel.configState.observe(viewLifecycleOwner) { state ->

            scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)

            when (state) {
                is ConfigState.EmptyDayTypesList -> {
                    binding.tvEmptyPattern.visibility = View.VISIBLE
                    binding.recyclerViewEditDayTypes.visibility = View.GONE

                    configViewModel.scheduleConfig.observe(viewLifecycleOwner) { config ->
                        binding.tvPatternNameValue.text = config.configName
                        binding.tvFirstWorkDateValue.text = config.firstWorkDate
                    }
                }
                is ConfigState.NotEmptyDayTypesList -> {
                    binding.tvEmptyPattern.visibility = View.GONE
                    binding.recyclerViewEditDayTypes.visibility = View.VISIBLE

                    configViewModel.scheduleConfig.observe(viewLifecycleOwner) { config ->
                        dayTypeListAdapter.submitList(config.schedulePattern)

                        binding.tvPatternNameValue.text = config.configName
                        binding.tvFirstWorkDateValue.text = config.firstWorkDate
                    }
                }
            }
        }

        configViewModel.dayTypeState.observe(viewLifecycleOwner) { state ->
            dayType = state.first
            dayTypePosition = state.second
        }
    }

    private fun setupButtons() {
        setupEditPatternNameButton()
        setupChooseFirstWorkDateButton()
        setOnCreateDefaultTypeClickListener()
        setupEditColorButton()
        setupEditDayTypeTitleButton()
        setOnDeleteButtonClickListener()
    }

    private fun setupEditPatternNameButton() {
        binding.buttonEditPatternName.setOnClickListener {
            val currentName = binding.tvPatternNameValue.text.toString()

            val dialogTitle = getString(R.string.edit_pattern_name_dialog_title)
            val hint = getString(R.string.edit_pattern_name_dialog_hint)

            EditTextDialogFragment.show(
                parentFragmentManager,
                currentName,
                dialogTitle,
                hint,
                EDIT_PATTERN_NAME_REQUEST_KEY
            )
        }

        EditTextDialogFragment.setupListener(
            parentFragmentManager,
            viewLifecycleOwner,
            EDIT_PATTERN_NAME_REQUEST_KEY
        ) { key, text ->
            if (key == EDIT_PATTERN_NAME_REQUEST_KEY) {
                configViewModel.obtainEvent(ConfigEvent.SavePatternName(text))
            }
        }
    }

    private fun setupChooseFirstWorkDateButton() {
        binding.firstWorkDateButton.setOnClickListener {
            DatePickerDialogFragment.show(parentFragmentManager)
        }

        DatePickerDialogFragment.setupListener(parentFragmentManager, viewLifecycleOwner) { date ->
            configViewModel.obtainEvent(ConfigEvent.SaveFirstWorkDate(date))
        }
    }

    private fun setOnCreateDefaultTypeClickListener() {
        binding.fButtonCreateType.setOnClickListener {

            configViewModel.obtainEvent(ConfigEvent.CreateDayType)
        }
    }

    private fun setupEditColorButton() {

        dayTypeListAdapter.onEditColorButtonClickListener = { position ->

            val dayType = dayTypeListAdapter.currentList[position]

            configViewModel.obtainEvent(ConfigEvent.SaveDatTypeState(dayType, position))

            ColorPickerDialogFragment.show(parentFragmentManager)
        }

        ColorPickerDialogFragment.setupListener(
            parentFragmentManager,
            viewLifecycleOwner
        ) { color ->
            dayType = dayType.copy(backgroundColor = color)
            configViewModel.obtainEvent(ConfigEvent.EditDayType(dayTypePosition, dayType))
        }
    }

    private fun setupEditDayTypeTitleButton() {

        dayTypeListAdapter.onEditTitleButtonClickListener = { position ->
            val dayType = dayTypeListAdapter.currentList[position]

            configViewModel.obtainEvent(ConfigEvent.SaveDatTypeState(dayType, position))

            val dialogTitle = getString(R.string.edit_title_dialog_title)
            val hint = getString(R.string.edit_title_dialog_hint)

            EditTextDialogFragment.show(
                parentFragmentManager,
                dayType.title,
                dialogTitle,
                hint,
                EDIT_DAY_TYPE_TITLE_REQUEST_KEY
            )
        }

        EditTextDialogFragment.setupListener(
            parentFragmentManager,
            viewLifecycleOwner,
            EDIT_DAY_TYPE_TITLE_REQUEST_KEY
        ) { key, text ->
            if (key == EDIT_DAY_TYPE_TITLE_REQUEST_KEY) {
                dayType = dayType.copy(title = text)
                configViewModel.obtainEvent(ConfigEvent.EditDayType(dayTypePosition, dayType))
            }
        }
    }

    private fun setOnDeleteButtonClickListener() {
        dayTypeListAdapter.onDeleteButtonClickListener = { position ->

            configViewModel.obtainEvent(ConfigEvent.DeleteDayType(position))
        }
    }

    companion object {
        private const val EDIT_PATTERN_NAME_REQUEST_KEY = "EDIT_PATTERN_NAME_REQUEST_KEY"
        private const val EDIT_DAY_TYPE_TITLE_REQUEST_KEY = "EDIT_DAY_TYPE_TITLE_REQUEST_KEY"

        fun newInstance(): ScheduleConfigFragment {
            return ScheduleConfigFragment()
        }
    }
}