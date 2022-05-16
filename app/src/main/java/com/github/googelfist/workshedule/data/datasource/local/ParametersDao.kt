package com.github.googelfist.workshedule.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.github.googelfist.workshedule.data.datasource.local.model.ConfigDao
import com.github.googelfist.workshedule.data.datasource.local.model.CurrentConfigIdDao

@Dao
interface ParametersDao {

    @Query("SELECT * FROM config_dao WHERE id = :id ")
    suspend fun loadConfigDao(id: Int): ConfigDao?

    @Transaction
    suspend fun saveConfigName(id: Int, configName: String) {
        insertConfigName(id, configName)
        updateConfigName(id, configName)
    }

    @Transaction
    suspend fun savePattern(id: Int, pattern: String) {
        insertPattern(id, pattern)
        updatePattern(id, pattern)
    }

    @Transaction
    suspend fun saveFirstWorkDate(id: Int, firstWorkDate: String) {
        insertFirstWorkDate(id, firstWorkDate)
        updateFirstWorkDate(id, firstWorkDate)
    }

    @Query("SELECT current_config_id FROM current_config WHERE id = :id ")
    suspend fun loadCurrentConfigId(id: Int): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCurrentConfigId(currentConfigIdDao: CurrentConfigIdDao)


    @Query("INSERT OR IGNORE INTO config_dao (id, config_name) VALUES (:id, :configName)")
    fun insertConfigName(id: Int, configName: String)

    @Query("UPDATE config_dao SET config_name = :configName WHERE id = :id")
    fun updateConfigName(id: Int, configName: String)


    @Query("INSERT OR IGNORE INTO config_dao (id, pattern) VALUES (:id, :pattern)")
    suspend fun insertPattern(id: Int, pattern: String)

    @Query("UPDATE config_dao SET pattern = :pattern WHERE id = :id")
    suspend fun updatePattern(id: Int, pattern: String)


    @Query("INSERT OR IGNORE INTO config_dao (id, first_work_date) VALUES (:id, :firstWorkDate)")
    suspend fun insertFirstWorkDate(id: Int, firstWorkDate: String)

    @Query("UPDATE config_dao SET first_work_date = :firstWorkDate WHERE id = :id")
    suspend fun updateFirstWorkDate(id: Int, firstWorkDate: String)
}