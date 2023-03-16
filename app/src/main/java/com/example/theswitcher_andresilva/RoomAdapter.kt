package com.example.theswitcher_andresilva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter(val roomList : List<Room>) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    var onItemClick : ((Room) -> Unit)? = null
    var onSwitchClick : ((Room, Boolean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_room, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentItem = roomList[position]
        holder.roomNameTextView.text = currentItem.name
        holder.roomSwitch.isChecked = currentItem.lightOn

        holder.roomNameTextView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }

        holder.roomSwitch.setOnCheckedChangeListener { _, isChecked ->
            onSwitchClick?.invoke(currentItem, isChecked)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roomSwitch : Switch = itemView.findViewById(R.id.switch_room)
        var roomNameTextView : TextView = itemView.findViewById(R.id.tv_room_item)
    }
}