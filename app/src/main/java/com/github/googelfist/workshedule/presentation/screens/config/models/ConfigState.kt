package com.github.googelfist.workshedule.presentation.screens.config.models

sealed class ConfigState {
    object EmptyDayTypesList : ConfigState()
    object NotEmptyDayTypesList : ConfigState()
}
