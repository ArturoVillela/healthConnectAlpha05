package com.firebaseapp.charleandroidblog.healthconnectalpha05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this;

        findViewById<Button>(R.id.button2).setOnClickListener({
            startActivity(Intent(context,HealthConnectActivityTest::class.java))
        })

    }
}