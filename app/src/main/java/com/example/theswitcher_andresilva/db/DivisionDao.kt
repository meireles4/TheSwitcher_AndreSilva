package com.example.theswitcher_andresilva.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DivisionDao {

    @Query("Select * from divisionTable")
    fun getAllDivisions(): LiveData<List<Division>>

    @Update
    fun update(division: Division)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (division: Division)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll (divisionList: List<Division>)

    @Delete
    fun delete(division: Division)
}