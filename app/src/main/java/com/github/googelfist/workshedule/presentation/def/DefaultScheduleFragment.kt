package com.github.googelfist.workshedule.presentation.def

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.DefaultScheduleFragmentBinding
import com.github.googelfist.workshedule.presentation.def.recycler.DefaultDayListAdapter
import javax.inject.Inject

class DefaultScheduleFragment : Fragment() {

    private var _binding: DefaultScheduleFragmentBinding? = null
    private val binding: DefaultScheduleFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var defaultViewModelFactory: DefaultViewModelFactory

    private val defaultViewModel by activityViewModels<DefaultViewModel> {
        defaultViewModelFactory
    }

    private lateinit var dayListAdapter: DefaultDayListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DefaultScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupButtons()

        setupRecyclerView()
    }

    private fun observeViewModel() {
        defaultViewModel.month.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.getDaysList())
            binding.defaultDateTextView.text = it.getFormattedDate()
        }
    }

    private fun setupButtons() {
        binding.defaultPreviousButton.setOnClickListener {
            defaultViewModel.onGeneratePreviousMonth()
        }
        binding.defaultCurrentButton.setOnClickListener {
            defaultViewModel.onGenerateCurrentMonth()
        }
        binding.defaultNextButton.setOnClickListener {
            defaultViewModel.onGenerateNextMonth()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.defaultDayRecyclerView

        dayListAdapter = DefaultDayListAdapter()
        recyclerView.adapter = dayListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun getNewInstance(): DefaultScheduleFragment {
            return DefaultScheduleFragment()
        }
    }
}