package com.github.googelfist.workshedule.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.googelfist.workshedule.data.datasource.local.model.ConfigDao
import com.github.googelfist.workshedule.data.datasource.local.model.CurrentConfigIdDao

@Database(
    entities = [ConfigDao::class, CurrentConfigIdDao::class],
    version = DB_VERSION,
    exportSchema = false
)

abstract class ParametersDataBase : RoomDatabase() {
    abstract fun getParametersDao(): ParametersDao
}

private const val DB_VERSION = 1