package com.github.googelfist.workshedule.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "first_work_date")
data class FirstWorkDateDao(
    @PrimaryKey
    val id: Int,
    val firstWorkDate: String
)