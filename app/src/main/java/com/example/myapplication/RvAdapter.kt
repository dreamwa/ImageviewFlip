package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(
    val touchActivity: TouchActivity,
    val lists: ArrayList<String>
) : RecyclerView.Adapter<RvAdapter.Viewholder>() {
    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.Viewholder {
        val view = LayoutInflater.from(touchActivity).inflate(R.layout.item_rv,parent,false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }


    override fun onBindViewHolder(holder: Viewholder, position: Int) {
    }

    class Viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }
}