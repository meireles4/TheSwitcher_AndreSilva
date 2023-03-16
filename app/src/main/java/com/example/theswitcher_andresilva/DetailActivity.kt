package com.example.theswitcher_andresilva

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val roomName = intent.getStringExtra("name")
        val lightStatus = intent.getBooleanExtra("lightStatus", false)

        val textView = findViewById<TextView>(R.id.tv_detail_screen)
        val textViewLightStatus = findViewById<TextView>(R.id.tv_detail_screen_light_status)
        val lightImage = findViewById<ImageView>(R.id.iv_light_state)

        if (lightStatus) {
            textView.text = "Your $roomName light is"
            textViewLightStatus.text = "ON"
            lightImage.setImageResource(R.drawable.light_on)

        }
        else {
            textView.text = "Your $roomName light is"
            textViewLightStatus.text = "OFF"
            lightImage.setImageResource(R.drawable.light_off)
        }

        val toolbarTextView = findViewById<TextView>(R.id.tv_tb_detail_screen)
        toolbarTextView.text = roomName

        val toolbarBackButton = findViewById<LinearLayout>(R.id.ll_back)
        toolbarBackButton.setOnClickListener {
            finish()
        }


    }
}