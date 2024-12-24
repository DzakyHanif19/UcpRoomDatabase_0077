package com.example.ucp2

import android.app.Application
import com.example.ucp2.depedenciesInjection.ContainerApp

class TokoApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate(){
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}