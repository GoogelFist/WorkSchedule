package com.github.googelfist.workschedule.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.presentation.PreferenceViewModel

class PreferenceViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository = PreferenceRepositoryImpl(context)
    private val loadPreferencesUseCase = LoadPreferencesUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PreferenceViewModel(loadPreferencesUseCase) as T
    }
}
