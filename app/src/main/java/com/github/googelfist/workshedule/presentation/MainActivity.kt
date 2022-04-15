package com.github.googelfist.workshedule.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel by viewModels<MainViewModel> {
        mainViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.month.observe(this) {
            binding.textView.text = it.getFormattedDate()
        }

        setupButtons()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_recycler_view_container,
                    DefaultScheduleFragment.getNewInstance()
                )
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun setupButtons() {
        binding.button1.setOnClickListener {
            mainViewModel.onGeneratePreviousMonth()
        }
        binding.button2.setOnClickListener {
            mainViewModel.onGenerateCurrentMonth()
        }
        binding.button3.setOnClickListener {
            mainViewModel.onGenerateNextMonth()
        }
    }
}