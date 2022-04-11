package com.laychv.router

import android.app.Application
import com.laychv.router_runtime.Router

class APP : Application() {

    override fun onCreate() {
        super.onCreate()
        Router.init()
    }
}