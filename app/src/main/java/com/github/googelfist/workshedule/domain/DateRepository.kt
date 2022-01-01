package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.presentation.settings.Preference
import java.time.LocalDate

interface DateRepository {
    fun savePreference(preference: Preference)

    fun loadPreference(): Preference
}