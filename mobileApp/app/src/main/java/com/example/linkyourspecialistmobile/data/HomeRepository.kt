package com.example.linkyourspecialistmobile.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.linkyourspecialistmobile.network.ApiClient
import com.example.linkyourspecialistmobile.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun signup(postModel: RegistrationUserModel): LiveData<RegistrationResponseModel> {
        val data = MutableLiveData<RegistrationResponseModel>()

        apiInterface?.signup(postModel)?.enqueue(object : Callback<RegistrationResponseModel> {
            override fun onFailure(call: Call<RegistrationResponseModel>, t: Throwable) {
                data.value = null
                Log.d("FAILURE", "FAIL")
            }

            override fun onResponse(
                call: Call<RegistrationResponseModel>,
                response: Response<RegistrationResponseModel>
            ) {
                val res = response.body()
                Log.d("RESPONSE", res.toString())
                if (response.code() == 201 && res != null) {
                    data.value = res
                    Log.d("API", "Successful")
                } else {
                    data.value = null
                    Log.d("API", "Error")
                }
            }
        })
        return data
    }
}
