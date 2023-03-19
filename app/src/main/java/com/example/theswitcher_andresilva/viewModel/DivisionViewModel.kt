package com.example.theswitcher_andresilva.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.db.DivisionDao
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

    private var dao: DivisionDao
    private var repository: DivisionRepository
    var allDivisions: LiveData<List<Division>>

    init {
        dao = DivisionDatabase.getInstance(application).getDivisionDao()
        repository = DivisionRepository(dao)
        allDivisions = repository.allDivisions
    }

    fun updateDivision (division: Division) = repository.update(division)

    fun insertDivision (division: Division) = repository.insert(division)

    fun deleteDivision (division: Division) = repository.delete(division)
}