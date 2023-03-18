package com.example.theswitcher_andresilva.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.db.DivisionDatabase
import com.example.theswitcher_andresilva.repository.DivisionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DivisionViewModel(application: Application) : AndroidViewModel(application){

    lateinit var allDivisions: ArrayList<Division>
    private val repository: DivisionRepository

    init {
        val dao = DivisionDatabase.getInstance(application).getDivisionDao()
        repository = DivisionRepository(dao)

        //use runBlocking to be able to call launch()
        runBlocking {
            launch(Dispatchers.IO){
                allDivisions = repository.getAllDivisions() as ArrayList<Division>
            }
            //using delay to ensure Coroutine finishes before RVAdapter is created
            // !! probably not a good solution !!
            delay(100)
        }
    }

    fun updateDivision (division: Division) {
        //update DB
        repository.update(division)

        //update ViewModel
        allDivisions = repository.getAllDivisions() as ArrayList<Division>
    }

    fun insertDivision (division: Division) {
        //update DB
        repository.insert(division)

        //update ViewModel
        allDivisions = repository.getAllDivisions() as ArrayList<Division>
    }
}