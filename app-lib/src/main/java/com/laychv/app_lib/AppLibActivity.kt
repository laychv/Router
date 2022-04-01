package com.laychv.app_lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laychv.annotation.Destination

@Destination(url = "router://module-app-lib", description = "其他模块中使用")
class AppLibActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}