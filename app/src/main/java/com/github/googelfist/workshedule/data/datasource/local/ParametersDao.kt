package com.github.googelfist.workshedule.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao

@Dao
interface ParametersDao {

    @Query("SELECT firstWorkDate FROM first_work_date WHERE id = :id ")
    suspend fun loadFirstWorkDate(id: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFirstWorkDate(firstWorkDateDao: FirstWorkDateDao)

    @Query("SELECT scheduleType FROM schedule_type WHERE id = :id")
    suspend fun loadScheduleType(id: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveScheduleType(scheduleTypeDao: ScheduleTypeDao)
}