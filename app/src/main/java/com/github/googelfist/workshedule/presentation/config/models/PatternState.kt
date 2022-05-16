package com.github.googelfist.workshedule.presentation.config.models

sealed class PatternState {
    object EmptyPattern : PatternState()
    object NormalState : PatternState()
}
