package com.github.googelfist.workshedule.presentation.settings

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivitySettingsBinding

    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        binding.tvDate.setOnClickListener { showDatePickerDialog() }

        setupArrayAdapter()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.saveSchedulePreference(parent?.adapter?.getItem(position).toString())
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = "$year-${month + 1}-$dayOfMonth"
        this.saveDatePreference(date)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment(this)
        newFragment.show(supportFragmentManager, FRAGMENT_TAG)
    }

    private fun setupArrayAdapter() {
        ArrayAdapter.createFromResource(
            this,
            R.array.preference_dropdown_items,
            R.layout.support_simple_spinner_dropdown_item
        ).also { arrayAdapter ->
            binding.spinnerSchedule.adapter = arrayAdapter
            binding.spinnerSchedule.onItemSelectedListener = this
        }
    }

    private fun saveDatePreference(date: String) {
        settingsViewModel.saveDatePreference(date)
    }

    private fun saveSchedulePreference(schedule: String) {
        settingsViewModel.saveSchedulePreference(schedule)
    }

    companion object {
        private const val FRAGMENT_TAG = "datePicker"
    }
}
