package com.github.googelfist.workshedule.presentation.twointwo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.TwoInTwoScheduleFragmentBinding
import com.github.googelfist.workshedule.presentation.twointwo.recycler.TwoInTwoDayListAdapter
import javax.inject.Inject

class TwoInTwoScheduleFragment : Fragment() {

    private var _binding: TwoInTwoScheduleFragmentBinding? = null
    private val binding: TwoInTwoScheduleFragmentBinding
        get() = _binding!!

    @Inject
    lateinit var twoInTwoViewModel: TwoInTwoViewModelFactory

    private val mainViewModel by activityViewModels<TwoInTwoViewModel> {
        twoInTwoViewModel
    }

    lateinit var dayListAdapter: TwoInTwoDayListAdapter

    override fun onAttach(context: Context) {
        context.component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TwoInTwoScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.month.observe(viewLifecycleOwner) {
            dayListAdapter.submitList(it.getDaysList())
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.twoInTwoDayRecyclerView

        dayListAdapter = TwoInTwoDayListAdapter()
        recyclerView.adapter = dayListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun getNewInstance(): TwoInTwoScheduleFragment {
            return TwoInTwoScheduleFragment()
        }
    }
}