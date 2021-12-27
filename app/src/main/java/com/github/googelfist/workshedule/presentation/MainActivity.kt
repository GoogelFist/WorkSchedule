package com.github.googelfist.workshedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.googelfist.workshedule.databinding.ActivityMainBinding

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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupNavigationButton()

        viewModel.dayList.observe(this) {
            dayListAdapter.dayList = it
        }
        viewModel.date.observe(this){
            binding.tvYearMonth.text = it
        }
    }

    private fun setupNavigationButton() {
        binding.ivMonthUp.setOnClickListener {
            viewModel.getPreviousMonth()
        }
        binding.ivMonthDown.setOnClickListener {
            viewModel.getNextMonth()
        }

    }

    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList
        rvDayList.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        dayListAdapter = DayListAdapter()
        rvDayList.adapter = dayListAdapter
    }

    companion object {
        const val SPAN_COUNT = 7
    }
}