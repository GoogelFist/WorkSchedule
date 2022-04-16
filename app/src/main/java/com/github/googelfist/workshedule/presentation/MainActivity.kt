package com.github.googelfist.workshedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ActivityMainBinding
import com.github.googelfist.workshedule.presentation.def.DefaultScheduleFragment
import com.github.googelfist.workshedule.presentation.twointwo.TwoInTwoScheduleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mode = DEFAULT_MODE
//    private val mode = TWO_IN_TWO_MODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if (savedInstanceState == null) {
            launch(mode)
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

    companion object {
        private const val TWO_IN_TWO_MODE = "TwoInTwo"
        private const val DEFAULT_MODE = "Default"
    }
}