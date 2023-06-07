package com.example.trackingyourscaswor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.work.WorkManager
import com.example.trackingyourscaswor.RouteTrackingService.Companion.EXTRA_SECRET_CAT_AGENT_ID

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchTrackingService()
    }

    private fun launchTrackingService() {
        RouteTrackingService.trackingCompletion.observe(this, Observer { agentId ->
            showResult("Agent $agentId arrived!")
        })
        val serviceIntent = Intent(this, RouteTrackingService::class.java).apply {
            putExtra(EXTRA_SECRET_CAT_AGENT_ID, "007")
        }
        ContextCompat.startForegroundService(this, serviceIntent)

    }

    fun showResult(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}