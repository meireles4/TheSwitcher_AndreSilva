package com.example.theswitcher_andresilva.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theswitcher_andresilva.DivisionListClickInterface
import com.example.theswitcher_andresilva.DivisionSwitchInterface
import com.example.theswitcher_andresilva.DivisionsRVAdapter
import com.example.theswitcher_andresilva.R
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.viewModel.DivisionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DivisionListClickInterface, DivisionSwitchInterface {

    lateinit var divisionsRV: RecyclerView
    lateinit var viewModel: DivisionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.green_toolbar)

        viewModel = ViewModelProvider (
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DivisionViewModel::class.java)

        divisionsRV = findViewById(R.id.rv_main_screen)
        divisionsRV.layoutManager = LinearLayoutManager(this)
        divisionsRV.setHasFixedSize(true)

        val divisionAdapter = DivisionsRVAdapter(this, this)
        divisionAdapter.updateList(viewModel.allDivisions)
        divisionsRV.adapter = divisionAdapter
    }

    override fun onListItemClick(division: Division) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", division.divisionName)
        intent.putExtra("lightStatus", division.lightStatus)
        startActivity(intent)
    }

    override fun onSwitchClick(updatedDivision: Division) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.updateDivision(updatedDivision)
        }
    }
}