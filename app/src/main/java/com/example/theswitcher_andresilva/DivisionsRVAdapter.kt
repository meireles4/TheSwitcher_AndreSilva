package com.example.theswitcher_andresilva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theswitcher_andresilva.db.Division

class DivisionsRVAdapter(
    private val divisionListClickInterface: DivisionListClickInterface,
    private val divisionSwitchInterface: DivisionSwitchInterface
) : RecyclerView.Adapter<DivisionsRVAdapter.DivisionViewHolder>() {

    private val allDivisions = ArrayList<Division>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DivisionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_division, parent, false)
        return DivisionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DivisionViewHolder, position: Int) {
        val currentDivision = allDivisions[position]
        holder.divisionNameTextView.text = currentDivision.divisionName
        holder.divisionSwitch.isChecked = currentDivision.lightStatus

        holder.divisionNameTextView.setOnClickListener {
            divisionListClickInterface.onListItemClick(currentDivision)
        }

        holder.divisionSwitch.setOnCheckedChangeListener { _, isChecked ->
            val updatedDivision = currentDivision
            updatedDivision.lightStatus = isChecked
            divisionSwitchInterface.onSwitchClick(updatedDivision)
        }
    }

    override fun getItemCount(): Int {
        return allDivisions.size
    }

    inner class DivisionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var divisionSwitch : Switch = itemView.findViewById(R.id.switch_division)
        var divisionNameTextView : TextView = itemView.findViewById(R.id.tv_division_item)
    }

    fun updateList(newList: List<Division>) {
        allDivisions.clear()
        allDivisions.addAll(newList)
        notifyDataSetChanged()
    }
}

interface DivisionListClickInterface {
    fun onListItemClick(division: Division)
}

interface DivisionSwitchInterface {
    fun onSwitchClick(updatedDivision: Division)
}
