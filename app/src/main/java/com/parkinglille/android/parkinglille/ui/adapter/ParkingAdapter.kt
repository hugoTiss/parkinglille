package com.parkinglille.android.parkinglille.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.parkinglille.android.parkinglille.R
import com.parkinglille.android.parkinglille.ui.viewholder.ParkingViewHolder
import data.RecordsItem

class ParkingAdapter(private var listParking:List<RecordsItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.activity_parking_item_list,parent,false)

        return ParkingViewHolder(view)
    }

    override fun getItemCount(): Int {

        return listParking.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ParkingViewHolder).bindData(listParking[position])
    }

    fun addListParking(list:List<RecordsItem>)
    {
        listParking=list
        notifyDataSetChanged()
    }


}