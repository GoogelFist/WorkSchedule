package com.github.googelfist.workshedule.domain

interface PreferenceRepository {
    fun loadPreference(): String
}
