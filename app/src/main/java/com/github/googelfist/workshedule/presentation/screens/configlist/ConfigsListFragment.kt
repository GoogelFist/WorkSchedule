package com.github.googelfist.workshedule.presentation.screens.configlist

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
import com.github.googelfist.workshedule.databinding.ConfigListFragmentBinding
import com.github.googelfist.workshedule.presentation.screens.config.ConfigViewModel
import com.github.googelfist.workshedule.presentation.screens.config.ConfigViewModelFactory
import com.github.googelfist.workshedule.presentation.screens.config.ScheduleConfigFragment
import com.github.googelfist.workshedule.presentation.screens.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.screens.configlist.models.ConfigListState
import com.github.googelfist.workshedule.presentation.screens.configlist.recycler.ConfigListAdapter
import com.github.googelfist.workshedule.presentation.screens.schedule.ScheduleViewModel
import com.github.googelfist.workshedule.presentation.screens.schedule.ScheduleViewModelFactory
import com.github.googelfist.workshedule.presentation.screens.schedule.models.ScheduleEvent
import javax.inject.Inject

class ConfigsListFragment : Fragment() {

    private var _binding: ConfigListFragmentBinding? = null
    private val binding: ConfigListFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var configViewModelFactory: ConfigViewModelFactory

    private val configViewModel by activityViewModels<ConfigViewModel> {
        configViewModelFactory
    }

    @Inject
    lateinit var scheduleViewModelFactory: ScheduleViewModelFactory

    private val scheduleViewModel by activityViewModels<ScheduleViewModel> {
        scheduleViewModelFactory
    }

    lateinit var configListAdapter: ConfigListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConfigListFragmentBinding.inflate(inflater, container, false)
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
        binding.recyclerViewConfigList.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewConfigList

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        configListAdapter = ConfigListAdapter()
        recyclerView.adapter = configListAdapter
    }

    private fun observeViewModel() {
        configViewModel.configListState.observe(viewLifecycleOwner) { state ->

            scheduleViewModel.obtainEvent(ScheduleEvent.RefreshMonth)

            when (state) {
                ConfigListState.EmptyList -> {
                    binding.tvEmptyConfigList.visibility = View.VISIBLE
                    binding.recyclerViewConfigList.visibility = View.GONE
                }
                ConfigListState.NotEmptyList -> {
                    binding.tvEmptyConfigList.visibility = View.GONE
                    binding.recyclerViewConfigList.visibility = View.VISIBLE

                    configViewModel.configList.observe(viewLifecycleOwner) { list ->
                        configListAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun setupButtons() {
        setOnEditPatternClickListener()
        setOnCreatePatternClickListener()
        setOnDeleteButtonClickListener()
        setOnChooseCurrentPatternClickListener()
    }

    private fun setOnEditPatternClickListener() {
        configListAdapter.onEditConfigButtonClickListener = { position ->

            val config = configListAdapter.currentList[position]
            configViewModel.obtainEvent(ConfigEvent.SaveCurrentConfigId(config.id))

            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_view_container,
                    ScheduleConfigFragment.newInstance()
                )
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun setOnCreatePatternClickListener() {
        binding.fButtonCreateConfig.setOnClickListener {
            configViewModel.obtainEvent(ConfigEvent.CreateConfig)
        }
    }

    private fun setOnDeleteButtonClickListener() {
        configListAdapter.onRemoveConfigButtonClickListener = { position ->
            val config = configListAdapter.currentList[position]
            configViewModel.obtainEvent(ConfigEvent.DeleteConfig(config.id))
        }
    }

    private fun setOnChooseCurrentPatternClickListener() {
        configListAdapter.onChooseCurrentPatternClickListener = { position ->
            val config = configListAdapter.currentList[position]
            configViewModel.obtainEvent(ConfigEvent.SaveCurrentConfigId(config.id))
        }
    }

    companion object {
        fun newInstance(): ConfigsListFragment {
            return ConfigsListFragment()
        }
    }
}