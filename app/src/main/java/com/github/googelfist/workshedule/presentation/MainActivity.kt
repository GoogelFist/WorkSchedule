package com.github.googelfist.workshedule.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.databinding.ActivityMainBinding
import com.github.googelfist.workshedule.presentation.adapters.DayListAdapter
import com.github.googelfist.workshedule.presentation.settings.SettingsActivity

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

        viewModel.generateCurrentMonth()
        viewModel.month.dayListLD.observe(this) { dayListAdapter.submitList(it) }
        viewModel.month.formattedDateLD.observe(this) { binding.tvYearMonth.text = it }

        setupButtons()

    }

    private fun setupGestureListener() {
        gestureListener = MainActivityGestureListener()
        gestureListener.onSwipeDown = { viewModel.generatePreviousMonth() }
        gestureListener.onSwipeUp = { viewModel.generateNextMonth() }
        gestureListener.onSwipeRight = { viewModel.generatePreviousMonth() }
        gestureListener.onSwipeLeft = { viewModel.generateNextMonth() }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupRecyclerView() {
        val rvDayList = binding.rvDayList

        dayListAdapter = DayListAdapter()
        rvDayList.adapter = dayListAdapter

        setRecyclerViewPool(rvDayList)

        rvDayList.setOnTouchListener { _, motionEvent -> mDetector.onTouchEvent(motionEvent) }

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
        binding.ivMonthUp.setOnClickListener { viewModel.generatePreviousMonth() }
        binding.ivMonthDown.setOnClickListener { viewModel.generateNextMonth() }

        binding.bSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    companion object {
        private const val SPAN_COUNT = 7
    }
}