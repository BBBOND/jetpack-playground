package com.bbbond.playground.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bbbond.playground.databinding.ActivityMainBinding
import com.bbbond.playground.windowpreferences.WindowPreferencesManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val windowPreferencesManager = WindowPreferencesManager(this)
        windowPreferencesManager.applyEdgeToEdgePreference(window)

        setContentView(binding.root)
        binding.btnCompose.setOnClickListener {
            startActivity(Intent(this@MainActivity, ComposeActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}