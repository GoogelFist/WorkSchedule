package com.github.googelfist.workshedule.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ActivityMainBinding
import com.github.googelfist.workshedule.presentation.def.DefaultScheduleFragment
import com.github.googelfist.workshedule.presentation.def.DefaultViewModel
import com.github.googelfist.workshedule.presentation.def.DefaultViewModelFactory
import com.github.googelfist.workshedule.presentation.twointwo.TwoInTwoScheduleFragment
import com.github.googelfist.workshedule.presentation.twointwo.TwoInTwoViewModel
import com.github.googelfist.workshedule.presentation.twointwo.TwoInTwoViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var defaultViewModelFactory: DefaultViewModelFactory

    private val defaultViewModel by viewModels<DefaultViewModel> {
        defaultViewModelFactory
    }

    @Inject
    lateinit var twoInTwoViewModelFactory: TwoInTwoViewModelFactory

    private val twoInTwoViewModel by viewModels<TwoInTwoViewModel> {
        twoInTwoViewModelFactory
    }

    //    private val mode = DEFAULT_MODE
    private val mode = TWO_IN_TWO_MODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        observeViewModels(mode)

        setupButtons(mode)

        if (savedInstanceState == null) {
            launch(mode)
        }
    }

    private fun observeViewModels(mode: String) {
        when (mode) {
            TWO_IN_TWO_MODE -> {
                twoInTwoViewModel.month.observe(this) {
                    binding.textView.text = it.getFormattedDate()
                }
            }
            else -> {
                defaultViewModel.month.observe(this) {
                    binding.textView.text = it.getFormattedDate()
                }
            }
        }
    }

    private fun launch(mode: String) {
        when (mode) {
            TWO_IN_TWO_MODE -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_recycler_view_container,
                        TwoInTwoScheduleFragment.getNewInstance()
                    )
                    .setReorderingAllowed(true)
                    .commit()
            }
            else -> {
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
    }

    private fun setupButtons(mode: String) {
        when (mode) {
            TWO_IN_TWO_MODE -> {
                binding.button1.setOnClickListener {
                    twoInTwoViewModel.onGeneratePreviousMonth()
                }
                binding.button2.setOnClickListener {
                    twoInTwoViewModel.onGenerateCurrentMonth()
                }
                binding.button3.setOnClickListener {
                    twoInTwoViewModel.onGenerateNextMonth()
                }
            }
            else -> {
                binding.button1.setOnClickListener {
                    defaultViewModel.onGeneratePreviousMonth()
                }
                binding.button2.setOnClickListener {
                    defaultViewModel.onGenerateCurrentMonth()
                }
                binding.button3.setOnClickListener {
                    defaultViewModel.onGenerateNextMonth()
                }
            }
        }
    }

    companion object {
        private const val TWO_IN_TWO_MODE = "TwoInTwo"
        private const val DEFAULT_MODE = "Default"
    }
}