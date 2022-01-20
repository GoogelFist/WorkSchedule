package com.github.googelfist.workschedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workschedule.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_container, MainActivityFragment.newInstance())
            .commit()
    }
}
