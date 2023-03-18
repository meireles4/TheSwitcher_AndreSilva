package com.example.theswitcher_andresilva.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisionTable")
class Division (
    @ColumnInfo(name = "divisionName") val divisionName:String,
    @ColumnInfo(name = "lightStatus") var lightStatus:Boolean)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}