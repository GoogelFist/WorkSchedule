package com.github.googelfist.workshedule.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.googelfist.workshedule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var dayListAdapter: DayListAdapter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var gestureListener: MainActivityGestureListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupGestureListener()

        mDetector = GestureDetectorCompat(this, gestureListener)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupNavigationButton()

        viewModel.dayList.observe(this) { dayListAdapter.dayList = it }
        viewModel.date.observe(this) { binding.tvYearMonth.text = it }
    }

    private fun setupGestureListener() {
        gestureListener = MainActivityGestureListener()
        gestureListener.onSwipeRight = { viewModel.getPreviousMonth() }
        gestureListener.onSwipeLeft = { viewModel.getNextMonth() }
    }

    private fun setupNavigationButton() {
        binding.ivMonthUp.setOnClickListener { viewModel.getPreviousMonth() }
        binding.ivMonthDown.setOnClickListener { viewModel.getNextMonth() }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        rvDayList.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        dayListAdapter = DayListAdapter()
        rvDayList.adapter = dayListAdapter

        rvDayList.setOnTouchListener { _, motionEvent -> mDetector.onTouchEvent(motionEvent) }

        dayListAdapter.onDayClickListener = {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val SPAN_COUNT = 7
    }
}