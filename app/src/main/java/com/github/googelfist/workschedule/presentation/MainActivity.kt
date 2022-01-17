package com.github.googelfist.workschedule.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workschedule.databinding.ActivityMainBinding
import com.github.googelfist.workschedule.presentation.adapters.DayListAdapter
import com.github.googelfist.workschedule.presentation.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var dayListAdapter: DayListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()

        viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

        viewModel.onGenerateCurrentMonth()
        viewModel.monthDTO.dayListLD.observe(this) { dayListAdapter.submitList(it) }
        viewModel.monthDTO.formattedDateLD.observe(this) { binding.tvYearMonth.text = it }

        setupButtons()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        dayListAdapter = DayListAdapter()
        rvDayList.adapter = dayListAdapter

        setRecyclerViewPool(rvDayList)

        rvDayList.onFlingListener = object : RecyclerViewSwipeListener() {
            override fun onSwipeUp() {
                viewModel.onGenerateNextMonth()
            }

            override fun onSwipeDown() {
                viewModel.onGeneratePreviousMonth()
            }
        }
        dayListAdapter.onDayClickListener = {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerViewPool(rvDayList: RecyclerView) {
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.ACTIVE_DAY_TYPE,
            DayListAdapter.ACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.INACTIVE_DAY_TYPE,
            DayListAdapter.INACTIVE_DAY_POOL_SIZE
        )
        rvDayList.recycledViewPool.setMaxRecycledViews(
            DayListAdapter.TODAY_TYPE,
            DayListAdapter.TODAY_DAY_POOL_SIZE
        )
    }

    private fun setupButtons() {
        binding.ivMonthUp.setOnClickListener { viewModel.onGeneratePreviousMonth() }
        binding.ivMonthDown.setOnClickListener { viewModel.onGenerateNextMonth() }

        binding.bSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
