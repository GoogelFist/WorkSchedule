package com.github.googelfist.workshedule

import android.app.Application
import android.content.Context
import com.github.googelfist.workshedule.di.ApplicationComponent
import com.github.googelfist.workshedule.di.DaggerApplicationComponent

class MainApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.create()
    }
}

val Context.component: ApplicationComponent
    get() = when (this) {
        is MainApplication -> component
        else -> this.applicationContext.component
    }
