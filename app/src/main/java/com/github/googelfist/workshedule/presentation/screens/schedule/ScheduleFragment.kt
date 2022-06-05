package com.github.googelfist.workshedule.presentation.screens.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ScheduleFragmentBinding
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.presentation.screens.config.ConfigViewModel
import com.github.googelfist.workshedule.presentation.screens.config.ConfigViewModelFactory
import com.github.googelfist.workshedule.presentation.screens.configlist.ConfigsListFragment
import com.github.googelfist.workshedule.presentation.screens.configlist.models.ConfigListState
import com.github.googelfist.workshedule.presentation.screens.schedule.models.ScheduleEvent
import com.github.googelfist.workshedule.presentation.screens.schedule.recycler.DayListAdapter
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

    @Inject
    lateinit var configViewModelFactory: ConfigViewModelFactory

    private val configViewModel by activityViewModels<ConfigViewModel> {
        configViewModelFactory
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

        setupRecyclerView()
        observeViewModel()
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        scheduleViewModel.scheduleState.observe(viewLifecycleOwner) { scheduleState ->
            when (scheduleState) {
                ScheduleState.Launching -> {
                    with(binding) {
                        progressBar.visibility = View.VISIBLE
                        binding.rootScheduleState.visibility = View.INVISIBLE
                        navigationButtons.visibility = View.INVISIBLE
                    }
                }

                is ScheduleState.GeneratedPrevious -> {
                    with(binding) {

                        ScheduleAnimationHelper.setAnimatingPreviousScheduleState(
                            scheduleState,
                            rootScheduleState,
                            dateTextView,
                            dayListAdapter,
                            navigationButtons
                        )
                    }
                }

                is ScheduleState.GeneratedCurrent -> {
                    with(binding) {

                        navigationButtons.visibility = View.VISIBLE

                        ScheduleAnimationHelper.setAnimatingCurrentScheduleState(
                            scheduleState,
                            rootScheduleState,
                            dateTextView,
                            dayListAdapter,
                            progressBar,
                            navigationButtons
                        )
                    }
                }

                is ScheduleState.GeneratedNext -> {
                    with(binding) {

                        ScheduleAnimationHelper.setAnimatingNextScheduleState(
                            scheduleState,
                            rootScheduleState,
                            dateTextView,
                            dayListAdapter,
                            navigationButtons
                        )
                    }
                }
            }
        }

        configViewModel.configListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ConfigListState.EmptyList -> {
                    binding.materialToolBar.title = null
                }
                is ConfigListState.NotEmptyList -> {
                    configViewModel.scheduleConfig.observe(viewLifecycleOwner) { config ->
                        binding.materialToolBar.title = config.configName
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView

        dayListAdapter = DayListAdapter()

        with(recyclerView) {
            adapter = dayListAdapter

            itemAnimator = null
        }
    }

    private fun setupButtons() {
        with(binding) {
            previousButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedPreviousMonth)
            }
            currentButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedCurrentMonth)
            }
            nextButton.setOnClickListener {
                scheduleViewModel.obtainEvent(ScheduleEvent.GeneratedNextMonth)
            }

            materialToolBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.settings -> {

                        createConfigListFragmentTransaction()

                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun createConfigListFragmentTransaction() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_view_container,
                ConfigsListFragment.newInstance()
            )
            .addToBackStack(null)
            .setReorderingAllowed(true)
            .commit()
    }

    companion object {

        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }
    }
}