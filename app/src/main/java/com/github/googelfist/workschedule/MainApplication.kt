package com.github.googelfist.workschedule

import android.app.Application
import android.content.Context
import com.github.googelfist.workschedule.di.ApplicationComponent
import com.github.googelfist.workschedule.di.DaggerApplicationComponent

class MainApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
            .builder()
            .context(this)
            .build()
    }
}

val Context.component: ApplicationComponent
    get() = when (this) {
        is MainApplication -> component
        else -> this.applicationContext.component
    }
