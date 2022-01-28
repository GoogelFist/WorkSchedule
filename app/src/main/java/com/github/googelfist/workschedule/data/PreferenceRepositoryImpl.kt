package com.github.googelfist.workschedule.data

import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import com.github.googelfist.workschedule.domain.PreferenceRepository
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    PreferenceRepository {
    override fun loadPreference(): PreferencesData {
        return preferenceDataSource.loadPreference()
    }
}
