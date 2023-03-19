package com.example.theswitcher_andresilva

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.viewModel.DivisionViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DivisionListClickInterface, DivisionSwitchInterface {

    lateinit var divisionsRV: RecyclerView
    lateinit var viewModel: DivisionViewModel
    lateinit var addDivisionFB: FloatingActionButton
    private var backPressedTime: Long = 0

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

        val divisionRVAdapter = DivisionsRVAdapter(this, this)
        divisionsRV.adapter = divisionRVAdapter

        //every time the list of divisions in the DB change, the observer listens to it
        //update the RV adapter and the UI will change automatically
        viewModel.allDivisions.observe(this) { list ->
            divisionRVAdapter.updateList(list)
        }

        addDivisionFB = findViewById(R.id.fb_add)
        addDivisionFB.setOnClickListener {
            val intent = Intent(this, AddDivisionActivity::class.java)
            startActivity(intent)
        }
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

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }


}