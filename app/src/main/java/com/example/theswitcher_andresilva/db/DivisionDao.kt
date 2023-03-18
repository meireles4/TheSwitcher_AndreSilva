package com.example.theswitcher_andresilva.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DivisionDao {

    @Query("Select * from divisionTable")
    fun getAllDivisions(): List<Division>

    @Update
    fun update(division: Division)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (division: Division)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll (divisionList: List<Division>)
}