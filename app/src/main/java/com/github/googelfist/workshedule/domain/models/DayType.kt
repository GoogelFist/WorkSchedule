package com.github.googelfist.workshedule.domain.models

data class DayType(
    val id: Int,
    val backgroundColor: String = DEFAULT_BACKGROUND_COLOR,
    val title: String = DEFAULT_TITLE
) {
    companion object {
        const val DEFAULT_BACKGROUND_COLOR = "#FFFFFFFF"
        const val DEFAULT_TITLE = "Default\nDay"
    }
}