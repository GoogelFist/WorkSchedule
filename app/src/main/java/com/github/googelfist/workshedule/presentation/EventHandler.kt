package com.github.googelfist.workshedule.presentation

interface EventHandler<E> {
    fun obtainEvent(event: E)
}