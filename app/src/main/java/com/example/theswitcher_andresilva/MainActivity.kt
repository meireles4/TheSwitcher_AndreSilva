package com.example.theswitcher_andresilva

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theswitcher_andresilva.repository.RoomRepository

class MainActivity : AppCompatActivity() {

    private lateinit var roomRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRecyclerView()
    }

    private fun startRecyclerView() {
        roomRecyclerView = findViewById(R.id.rv_main_screen)
        roomRecyclerView.layoutManager = LinearLayoutManager(this)
        roomRecyclerView.setHasFixedSize(true)

        val roomAdapter = RoomAdapter(RoomRepository.getAllRooms())
        roomAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it.name)
            intent.putExtra("lightStatus", it.lightOn)
            startActivity(intent)
        }

        roomAdapter.onSwitchClick = { room: Room, lightStatus: Boolean ->
            val index = roomAdapter.roomList.indexOf(room)
            roomAdapter.roomList[index].lightOn = lightStatus
        }

        roomRecyclerView.adapter = roomAdapter
    }
}