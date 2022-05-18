package com.github.googelfist.workshedule.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "config_dao")
data class ConfigDao(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "config_name")
    val configName: String?,
    @ColumnInfo(name = "first_work_date")
    val firstWorkDate: String?,
    @ColumnInfo(name = "pattern")
    val schedulePattern: String?
)