package com.yagmurceliksoy.bookapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yagmurceliksoy.bookapp.MainApplication
import com.yagmurceliksoy.bookapp.common.viewBinding
import com.yagmurceliksoy.bookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MainApplication.provideRetrofit(this)
    }
}