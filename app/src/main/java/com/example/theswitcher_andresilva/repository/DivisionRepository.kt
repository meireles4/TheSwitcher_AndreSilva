package com.example.theswitcher_andresilva.repository

import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.db.DivisionDao

class DivisionRepository(private val divisionDao: DivisionDao) {

    fun update(division: Division) {
        divisionDao.update(division)
    }

    fun getAllDivisions(): List<Division> {
        return divisionDao.getAllDivisions()
    }

    fun insert(division: Division) {
        return divisionDao.insert(division)
    }

    fun insertAll(divisionList: List<Division>) {
        return divisionDao.insertAll(divisionList)
    }

}