package com.example.theswitcher_andresilva

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.viewModel.DivisionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDivisionActivity : AppCompatActivity() {

    lateinit var nameET: EditText
    lateinit var addBTN: Button
    lateinit var viewModel: DivisionViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_division)

        window.statusBarColor = ContextCompat.getColor(this, R.color.green_toolbar)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DivisionViewModel::class.java)

        nameET = findViewById(R.id.et_add_screen)
        addBTN = findViewById(R.id.bt_add_division)

        addBTN.setOnClickListener {
            val newDivisionName = nameET.text.toString()

            //case user did not enter any name
            if (newDivisionName.isBlank()){
                Toast.makeText(this,
                    "You must enter a division name",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                val newDivision = Division(newDivisionName, false)
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.insertDivision(newDivision)
                }
                Toast.makeText( this, "Division Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        val toolbarBackButton = findViewById<LinearLayout>(R.id.ll_back_button_add_screen)
        toolbarBackButton.setOnClickListener {
            finish()
        }
    }
}