package com.github.googelfist.workschedule.presentation.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.databinding.PagerFragmentBinding
import com.github.googelfist.workschedule.di.workschedule.DaggerWorkComponent
import com.github.googelfist.workschedule.di.workschedule.WorkSchedule
import com.github.googelfist.workschedule.presentation.fragments.PreferenceFragment
import com.github.googelfist.workschedule.presentation.viewmodel.ScheduleViewModel
import com.github.googelfist.workschedule.presentation.viewmodel.factory.TwoInTwoScheduleViewModelFactory
import javax.inject.Inject

class WorkSchedulePagerFragment : Fragment() {

    private var _binding: PagerFragmentBinding? = null
    private val binding: PagerFragmentBinding
        get() = _binding!!

    private lateinit var pagerAdapter: WorkSchedulePagerAdapter

    private val component by lazy {
        LazyThreadSafetyMode.NONE
        DaggerWorkComponent.builder().context(requireActivity().application).build()
    }

    @Inject
    @WorkSchedule
    lateinit var twoInTwoScheduleViewModelFactory: TwoInTwoScheduleViewModelFactory

    private val viewModel: ScheduleViewModel by lazy {
        LazyThreadSafetyMode.NONE
        ViewModelProvider(
            requireActivity(),
            twoInTwoScheduleViewModelFactory
        )[ScheduleViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()

        setupButtons()
        viewModel.formatDateLD.observe(viewLifecycleOwner) { binding.tvYearMonth.text = it }
    }

    private fun setupViewPager() {
        pagerAdapter = WorkSchedulePagerAdapter(this)
        binding.pager.adapter = pagerAdapter
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.pager.setCurrentItem(ONE_VALUE, false)

        val recyclerView = binding.pager.getChildAt(ZERO_VALUE) as RecyclerView
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val itemCount = binding.pager.adapter?.itemCount ?: ZERO_VALUE

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstItemVisible = layoutManager.findFirstVisibleItemPosition()
                val lastItemVisible = layoutManager.findLastVisibleItemPosition()

                if (firstItemVisible == (itemCount - ONE_VALUE) && dx > ZERO_VALUE) {
                    viewModel.onGenerateNextMonth()
                    recyclerView.scrollToPosition(ONE_VALUE)
                } else if (lastItemVisible == ZERO_VALUE && dx < ZERO_VALUE) {
                    viewModel.onGeneratePreviousMonth()
                    recyclerView.scrollToPosition(itemCount - TWO_VALUE)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupButtons() {
        binding.includeNavigationPanel.ivMonthUp.setOnClickListener { viewModel.onGeneratePreviousMonth() }
        binding.includeNavigationPanel.ivMonthDown.setOnClickListener { viewModel.onGenerateNextMonth() }
        binding.fbCurrentMonth.setOnClickListener { viewModel.onGenerateCurrentMonth() }

        binding.ivSettings.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.schedule_activity_container, PreferenceFragment.newInstance())
                .setReorderingAllowed(true)
                .commit()
        }
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val ONE_VALUE = 1
        private const val TWO_VALUE = 2

        fun newInstance(): WorkSchedulePagerFragment {
            return WorkSchedulePagerFragment()
        }
    }
}
