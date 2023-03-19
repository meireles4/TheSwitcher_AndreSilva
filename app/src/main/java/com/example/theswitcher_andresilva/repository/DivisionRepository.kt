package com.example.theswitcher_andresilva.repository

import androidx.lifecycle.LiveData
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.db.DivisionDao

class DivisionRepository(private val divisionDao: DivisionDao) {

    val allDivisions: LiveData<List<Division>> = divisionDao.getAllDivisions()

    fun update(division: Division) = divisionDao.update(division)

    fun insert(division: Division) = divisionDao.insert(division)

    fun insertAll(divisionList: List<Division>) = divisionDao.insertAll(divisionList)

    fun delete(division: Division) = divisionDao.delete(division)

}