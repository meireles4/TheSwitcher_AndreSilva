package com.example.theswitcher_andresilva.repository

import com.example.theswitcher_andresilva.Room

object RoomRepository {

    fun getAllRooms(): List<Room> = rooms

    private val rooms = listOf(
        Room("Kitchen", false),
        Room("Living room", false),
        Room("Master bedroom", false),
        Room("Guest's bedroom", false)
    )
}
