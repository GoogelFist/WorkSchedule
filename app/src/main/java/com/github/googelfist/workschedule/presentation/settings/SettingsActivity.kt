package com.github.googelfist.workschedule.presentation.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.presentation.MainActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }

    override fun onPause() {
        startActivity(Intent(this, MainActivity::class.java))
        super.onPause()
    }
}
