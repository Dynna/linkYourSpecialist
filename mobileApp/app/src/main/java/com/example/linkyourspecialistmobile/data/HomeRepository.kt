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

    fun signup(registrationRequestModel: RegistrationRequestModel): LiveData<RegistrationResponseModel> {
        val data = MutableLiveData<RegistrationResponseModel>()

        apiInterface?.signup(registrationRequestModel)
            ?.enqueue(object : Callback<RegistrationResponseModel> {
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
                        data.value = res!!
                        Log.d("API", "Successful")
                    } else {
                        data.value = null
                        Log.d("API", "Error")
                    }
                }
            })
        return data
    }

    fun login(loginRequestModel: LoginRequestModel): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()

        apiInterface?.login(loginRequestModel)?.enqueue(object : Callback<LoginResponseModel> {
            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                data.value = null
                Log.d("FAILURE", "FAIL")
            }

            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                val res = response.body()
                Log.d("RESPONSE", res.toString())
                if (response.code() == 200 && res != null) {
                    data.value = res!!
                    Log.d("API", "Successful")
                } else {
                    data.value = null
                    Log.d("API", "Error")
                }
            }
        })
        return data
    }

    fun getPosts(authorizationHeader: String?, userid: String?): LiveData<MutableList<PostModelResponse>?> {

        var posts = MutableLiveData<MutableList<PostModelResponse>>()

        apiInterface?.getPosts(authorizationHeader, userid)
            ?.enqueue(object : Callback<MutableList<PostModelResponse>> {
                override fun onFailure(call: Call<MutableList<PostModelResponse>>, t: Throwable) {
                    posts.value = mutableListOf()
                    Log.d("GET_POSTS", "FAIL")
                }

                override fun onResponse(
                    call: Call<MutableList<PostModelResponse>>,
                    response: Response<MutableList<PostModelResponse>>
                ) {
                    val res: MutableList<PostModelResponse>? = response.body()
                    //Log.d("POSTS", res.toString())
                    if (response.code() == 200 && res != null) {
                        posts.value = res!!
                        Log.d("GET_POSTS", "Successful")
                    } else {
                        posts.value = mutableListOf()
                        Log.d("GET_POSTS", "Error")
                    }
                }
            })
        return posts
    }

    fun createPost(authorizationHeader: String?,newPostModel: NewPostModel){

        apiInterface?.createPost(authorizationHeader,newPostModel)?.enqueue(object : Callback<NewPostModel> {
            override fun onFailure(call: Call<NewPostModel>, t: Throwable) {
                Log.d("API_NEW_POST", "FAIL")
            }

            override fun onResponse(
                call: Call<NewPostModel>,
                response: Response<NewPostModel>
            ) {
                Log.d("RESPONSE_CODE", response.code().toString())
                if (response.code() == 201) {
                    Log.d("API_NEW_POST", "Post Created Successfully")
                } else {
                    Log.d("API_NEW_POST", "Post Not Created")
                }
            }
        })
    }
}
