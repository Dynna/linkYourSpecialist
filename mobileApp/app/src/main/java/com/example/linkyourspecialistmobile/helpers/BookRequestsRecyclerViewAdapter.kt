package com.example.linkyourspecialistmobile.helpers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.data.BookRequestModel
import com.example.linkyourspecialistmobile.databinding.RequestItemBinding

class BookRequestsRecyclerViewAdapter :
    RecyclerView.Adapter<BookRequestsRecyclerViewAdapter.MyViewHolder>() {

    private var requestsList: MutableList<BookRequestModel>? = mutableListOf()


    inner class MyViewHolder(binding: RequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val request: TextView = binding.request
        private val date: TextView = binding.date
        private val startTime: TextView = binding.startTime
        private val endTime: TextView = binding.endTime
        private val approveButton: Button = binding.approveButton
        private val rejectButton: Button = binding.rejectButton

        @SuppressLint("SetTextI18n")
        fun bind(item: BookRequestModel) {
            var dateFormat = item.date.toString().split("T")
            itemView.apply {
                request.text = request.text.toString() + " " + item.clientEmail
                date.text = "Date (yyyy/mm/dd): " + dateFormat[0]
                startTime.text = "Start Time: " + item.startTime
                endTime.text = "End Time: " + item.endTime
                approveButton.setOnClickListener {

                }
                rejectButton.setOnClickListener {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(requestsList!![position])
    }

    fun setData(newList: MutableList<BookRequestModel>?) {
        requestsList = newList?.toMutableList()
        notifyDataSetChanged()
    }
}
