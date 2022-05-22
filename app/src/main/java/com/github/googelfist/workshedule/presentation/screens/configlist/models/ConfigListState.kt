package com.github.googelfist.workshedule.presentation.screens.configlist.models

sealed class ConfigListState {
    object EmptyList : ConfigListState()
    object NotEmptyList : ConfigListState()
}
