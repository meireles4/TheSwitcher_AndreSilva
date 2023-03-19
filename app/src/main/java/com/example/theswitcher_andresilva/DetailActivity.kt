package com.example.theswitcher_andresilva

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.theswitcher_andresilva.R
import com.example.theswitcher_andresilva.db.Division
import com.example.theswitcher_andresilva.viewModel.DivisionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: DivisionViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.statusBarColor = ContextCompat.getColor(this, R.color.green_toolbar)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DivisionViewModel::class.java)

        val roomName = intent.getStringExtra("name") ?: "ERROR"
        val lightStatus = intent.getBooleanExtra("lightStatus", false)

        val textView = findViewById<TextView>(R.id.tv_detail_screen)
        val textViewLightStatus = findViewById<TextView>(R.id.tv_detail_screen_light_status)
        val lightImage = findViewById<ImageView>(R.id.iv_light_state)

        if (lightStatus) {
            textView.text = "Your $roomName light is"
            textViewLightStatus.text = "ON"
            lightImage.setImageResource(R.drawable.light_on)

        }
        else {
            textView.text = "Your $roomName light is"
            textViewLightStatus.text = "OFF"
            lightImage.setImageResource(R.drawable.light_off)
        }

        val toolbarTextView = findViewById<TextView>(R.id.tv_tb_detail_screen)
        toolbarTextView.text = roomName

        val toolbarBackButton = findViewById<LinearLayout>(R.id.ll_back)
        toolbarBackButton.setOnClickListener {
            finish()
        }

        val deleteButton = findViewById<Button>(R.id.bt_delete_division)
        deleteButton.setOnClickListener {
            showDeleteConfirmationDialog( Division(roomName, lightStatus) )
        }
    }

    private fun showDeleteConfirmationDialog(division: Division) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this division?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.deleteDivision(division)
                }
                Toast.makeText( this, "Division ${division.divisionName} deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}