package com.github.googelfist.workschedule.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.presentation.PreferenceViewModel

class PreferenceViewModelFactory(application: Application) : ViewModelProvider.Factory {
    private val repository = PreferenceRepositoryImpl(application)
    private val loadPreferencesUseCase = LoadPreferencesUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PreferenceViewModel(loadPreferencesUseCase) as T
    }
}
