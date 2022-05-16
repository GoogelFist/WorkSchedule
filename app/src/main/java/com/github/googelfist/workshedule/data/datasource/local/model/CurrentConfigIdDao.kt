package com.github.googelfist.workshedule.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_config")
data class CurrentConfigIdDao(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "current_config_id")
    val currentConfigId: Int
)