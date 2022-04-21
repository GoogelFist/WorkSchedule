package com.github.googelfist.workshedule.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_type")
data class ScheduleTypeDao(
    @PrimaryKey
    val id: Int,
    val scheduleType: String
)