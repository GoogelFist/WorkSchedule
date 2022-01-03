package com.github.googelfist.workshedule.domain.models

data class DatePreference(
    var date: String = DEFAULT_DATE
)

private const val DEFAULT_DATE = "Default date"