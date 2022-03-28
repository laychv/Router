package com.laychv.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laychv.annotation.Destination
import com.laychv.router.databinding.ActivityMainBinding

@Destination(url = "router://page-home", description = "应用主页")
class MainActivity : AppCompatActivity() {

    lateinit var inflate: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)
    }
}