package com.github.googelfist.workshedule.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao

@Dao
interface ParametersDao {

    @Query("SELECT firstWorkDate FROM first_work_date WHERE id = :id ")
    fun loadFirstWorkDate(id: Int): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFirstWorkDate(firstWorkDateDao: FirstWorkDateDao)

    @Query("SELECT scheduleType FROM schedule_type WHERE id = :id")
    fun loadScheduleType(id: Int): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveScheduleType(scheduleTypeDao: ScheduleTypeDao)
}