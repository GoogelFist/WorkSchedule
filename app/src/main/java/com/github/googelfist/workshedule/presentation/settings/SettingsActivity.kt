package com.github.googelfist.workshedule.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.googelfist.workshedule.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.flSettings.id, SettingsFragment())
            .commit()
    }
}