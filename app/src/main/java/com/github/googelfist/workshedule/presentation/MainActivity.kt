package com.github.googelfist.workshedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.component
import com.github.googelfist.workshedule.databinding.ActivityMainBinding
import com.github.googelfist.workshedule.presentation.schedule.ScheduleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_recycler_view_container,
                    ScheduleFragment.getNewInstance()
                )
                .setReorderingAllowed(true)
                .commit()
        }
    }
}