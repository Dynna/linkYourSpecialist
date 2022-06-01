package com.example.linkyourspecialistmobile.helpers

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.data.BookRequestModel
import com.example.linkyourspecialistmobile.data.BookResponseModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.databinding.RequestItemBinding
import com.example.linkyourspecialistmobile.viewmodel.BookRequestViewModel

class BookRequestsRecyclerViewAdapter :
    RecyclerView.Adapter<BookRequestsRecyclerViewAdapter.MyViewHolder>() {
    private var requestsList: MutableList<BookRequestModel>? = mutableListOf()
    val homeRepository = HomeRepository()
    private lateinit var userSharedPreferences: SharedPreferences
    private lateinit var activity: FragmentActivity

    inner class MyViewHolder(binding: RequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val request: TextView = binding.request
        private val date: TextView = binding.date
        private val startTime: TextView = binding.startTime
        private val endTime: TextView = binding.endTime
        private val approveButton: Button = binding.approveButton
        private val rejectButton: Button = binding.rejectButton
        private val description: TextView = binding.description

        @SuppressLint("SetTextI18n")
        fun bind(item: BookRequestModel, activity: FragmentActivity) {
            var dateFormat = item.date.toString().split("T")
            var bookResponseModel = BookResponseModel(
                item.specialistID,
                item.clientID,
                item.clientEmail,
                item.availabilityItemID
            )
            userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
            val accessToken: String = "Bearer " +
                    userSharedPreferences.getString("access_token", "not logged in").toString()

            itemView.apply {
                request.text = "Request to book time availability slot by: " + item.clientEmail
                date.text = "Date (yyyy/mm/dd): " + dateFormat[0]
                startTime.text = "Start Time: " + item.startTime
                endTime.text = "End Time: " + item.endTime
                description.text = "Description: " + item.description
                approveButton.setOnClickListener {
                    homeRepository.approveBookRequest(accessToken, bookResponseModel)
                }
                rejectButton.setOnClickListener {
                    homeRepository.declineBookRequest(accessToken, bookResponseModel)
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
        holder.bind(requestsList!![position], activity)
    }

    fun setData(newList: MutableList<BookRequestModel>?, myActivity: FragmentActivity) {
        requestsList = newList?.toMutableList()
        activity = myActivity
        notifyDataSetChanged()
    }

/*    fun setData(newList: MutableList<BookRequestModel>?) {
        requestsList = newList?.toMutableList()
        notifyDataSetChanged()
    }*/
}
