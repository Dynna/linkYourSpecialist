package com.example.linkyourspecialistmobile.ui.navigationfragments.availability

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.data.AvailabilityItemModel
import com.example.linkyourspecialistmobile.data.HomeRepository
import com.example.linkyourspecialistmobile.databinding.FragmentNewAvailabilityItemBinding
import java.text.SimpleDateFormat
import java.util.*

class NewAvailabilityItemFragment : Fragment() {

    private lateinit var binding: FragmentNewAvailabilityItemBinding
    private lateinit var date: EditText
    private lateinit var startTime: EditText
    private lateinit var endTime: EditText
    private lateinit var createButton: Button
    private lateinit var description: EditText
    private val calendar = Calendar.getInstance()
    private lateinit var userSharedPreferences: SharedPreferences
    private var homeRepository: HomeRepository? = HomeRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSharedPreferences = activity?.getSharedPreferences("UserData", 0)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewAvailabilityItemBinding.inflate(inflater, container, false)
        date = binding.date
        startTime = binding.startTime
        endTime = binding.endTime
        createButton = binding.createButton
        description = binding.description

        setCalendar()
        setStartTime()
        setEndTime()

        createButton.setOnClickListener {
            createItem()
            findNavController().navigate(R.id.availabilityFragment)
        }

        return binding.root
    }

    private fun createItem() {
        val itemDescription = binding.description.text.toString()
        val itemDate = binding.date.text.toString()
        val itemStartTime = binding.startTime.text.toString()
        val itemEndTime = binding.endTime.text.toString()
        val userid: String =
            userSharedPreferences.getString("id", "not found").toString()
        val accessToken: String = "Bearer " +
                userSharedPreferences.getString("access_token", "not logged in").toString()

        val availabilityItem = AvailabilityItemModel(
            null,
            userid,
            itemDate,
            itemStartTime,
            itemEndTime,
            itemDescription
        )
        homeRepository?.createAvailabilityItem(accessToken, availabilityItem)
    }

    private fun setCalendar() {

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        date.setOnClickListener {
            DatePickerDialog(
                context!!,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat)
        date!!.setText(sdf.format(calendar.time))
    }

    private fun setStartTime() {
        startTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                startTime.setText(SimpleDateFormat("HH:mm").format(calendar.time))
            }
            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setEndTime() {
        endTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                endTime.setText(SimpleDateFormat("HH:mm").format(calendar.time))
            }
            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}
