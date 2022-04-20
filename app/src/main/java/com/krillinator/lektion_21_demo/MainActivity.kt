package com.krillinator.lektion_21_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.krillinator.lektion_21_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Saves time and effort!
        viewBinding.apply {
            theCrazyStuff.setOnClickListener() {
                println("hey")
                Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
            }
        }


        // Start Service
        Intent(this, RandomService::class.java).also { intent ->
            startService(intent)
        }

    }
}