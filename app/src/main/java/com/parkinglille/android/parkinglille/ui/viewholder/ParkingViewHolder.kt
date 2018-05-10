package com.parkinglille.android.parkinglille.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import data.RecordsItem
import kotlinx.android.synthetic.main.activity_parking_item_list.view.*


class ParkingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindData(parking:RecordsItem){

        itemView.activityParkingItemLibelle.text=parking.fields.libelle
        itemView.activityParkingItemDispo.text= parking.fields.dispo.toString()
        itemView.activityParkingItemLastUpdate.text=parking.fields.datemaj


    }

}