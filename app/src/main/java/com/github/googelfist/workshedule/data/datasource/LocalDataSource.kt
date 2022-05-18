package com.github.googelfist.workshedule.data.datasource

import com.github.googelfist.workshedule.data.datasource.local.model.ConfigDao

interface LocalDataSource {
    suspend fun saveFirstWorkDate(id: Int, firstWorkDate: String)
    suspend fun saveConfigName(id: Int, configName: String)
    suspend fun savePattern(id: Int, pattern: String)

    suspend fun loadConfigDao(id: Int): ConfigDao?

    suspend fun saveCurrentConfigId(currentConfigId: Int)
    suspend fun loadCurrentConfigId(): Int

}