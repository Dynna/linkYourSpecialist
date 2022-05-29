package com.example.linkyourspecialistmobile.helpers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.databinding.AvailabilityItemBinding

class AvailabilityRecyclerViewAdapter :
    RecyclerView.Adapter<AvailabilityRecyclerViewAdapter.MyViewHolder>() {

    private var availabilityItemsList: MutableList<AvailabilityItemModel>? = mutableListOf()


    inner class MyViewHolder(binding: AvailabilityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val description: TextView = binding.availabilityDescription
        private val date: TextView = binding.availabilityDate
        private val startTime: TextView = binding.availabilityStartTime
        private val endTime: TextView = binding.availabilityEndTime

        @SuppressLint("SetTextI18n")
        fun bind(item: AvailabilityItemModel) {
            var dateFormat = item.date.toString().split("T")
            itemView.apply {
                if (item.description == "" || item.description == null) {
                    description.text = "Available on:"
                } else {
                    description.text = item.description + ":"
                }
                date.text = "Date (yyyy/mm/dd): " + dateFormat[0]
                startTime.text = "Start Time: " + item.startTime
                endTime.text = "End Time: " + item.endTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            AvailabilityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return availabilityItemsList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(availabilityItemsList!![position])
    }

    fun setData(newList: MutableList<AvailabilityItemModel>?) {
        availabilityItemsList = newList?.toMutableList()
        notifyDataSetChanged()
    }
}
