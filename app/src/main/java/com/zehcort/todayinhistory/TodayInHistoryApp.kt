package com.zehcort.todayinhistory

import android.app.Application

class TodayInHistoryApp : Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator()
    }
}