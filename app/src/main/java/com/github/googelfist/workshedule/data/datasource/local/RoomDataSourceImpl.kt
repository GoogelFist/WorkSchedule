package com.github.googelfist.workshedule.data.datasource.local

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.model.ConfigDao
import com.github.googelfist.workshedule.data.datasource.local.model.CurrentConfigIdDao
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val parametersDao: ParametersDao
) : LocalDataSource {

    override suspend fun loadConfigDao(id: Int): ConfigDao? {
        return parametersDao.loadConfigDao(id)
    }

    override suspend fun saveCurrentConfigId(currentConfigId: Int) {
        parametersDao.saveCurrentConfigId(CurrentConfigIdDao(DEFAULT_ID, currentConfigId))
    }

    override suspend fun loadCurrentConfigId(): Int {
        parametersDao.loadCurrentConfigId(DEFAULT_ID)?.let {
            return it
        }
        return DEFAULT_CURRENT_ID
    }

    override suspend fun saveFirstWorkDate(id: Int, firstWorkDate: String) {
        parametersDao.saveFirstWorkDate(id, firstWorkDate)
    }

    override suspend fun saveConfigName(id: Int, configName: String) {
        parametersDao.saveConfigName(id, configName)
    }

    override suspend fun savePattern(id: Int, pattern: String) {
        parametersDao.savePattern(id, pattern)
    }

    companion object {
        private const val DEFAULT_ID = 1
        private const val DEFAULT_CURRENT_ID = 1
    }
}