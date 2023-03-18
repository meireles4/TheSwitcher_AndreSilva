package com.example.theswitcher_andresilva.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.db.DivisionDatabase
import com.example.theswitcher_andresilva.repository.DivisionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DivisionViewModel(application: Application) : AndroidViewModel(application){

    lateinit var allDivisions: ArrayList<Division>
    private val repository: DivisionRepository

    init {
        val dao = DivisionDatabase.getInstance(application).getDivisionDao()
        repository = DivisionRepository(dao)
        runBlocking {
            val job = launch(Dispatchers.IO){
                allDivisions = repository.getAllDivisions() as ArrayList<Division>
            }
            //Wait for data to be loaded
            job.join()
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