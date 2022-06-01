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

    fun getPosts(
        authorizationHeader: String?,
        userid: String?
    ): LiveData<MutableList<PostModelResponse>?> {

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

    fun createPost(authorizationHeader: String?, newPostModel: NewPostModel) {

        apiInterface?.createPost(authorizationHeader, newPostModel)
            ?.enqueue(object : Callback<NewPostModel> {
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

    fun updateProfile(
        authorizationHeader: String?,
        userid: String?,
        updateProfileRequestModel: UpdateProfileRequestModel
    ): LiveData<RegistrationResponseModel?> {
        var data = MutableLiveData<RegistrationResponseModel?>()
        apiInterface?.updateProfile(authorizationHeader, userid, updateProfileRequestModel)
            ?.enqueue(object : Callback<RegistrationResponseModel> {
                override fun onFailure(call: Call<RegistrationResponseModel>, t: Throwable) {
                    data.value = null
                    Log.d("UPDATE_PROFILE", "FAIL")
                }

                override fun onResponse(
                    call: Call<RegistrationResponseModel>,
                    response: Response<RegistrationResponseModel>
                ) {
                    val res = response.body()
                    //Log.d("UPDATE_PROFILE_RESPONSE", res.toString())
                    if (response.code() == 200 && res != null) {
                        data.value = res!!
                        //Log.d("DATA", data.value.toString())
                        Log.d("API_UPDATE_PROFILE", "Successful")
                    } else {
                        data.value = null
                        Log.d("API_UPDATE_PROFILE", "Error")
                    }
                }
            })
        return data
    }

    fun createAvailabilityItem(
        authorizationHeader: String?,
        availabilityItemModel: AvailabilityItemModel
    ) {

        apiInterface?.createAvailabilityItem(authorizationHeader, availabilityItemModel)
            ?.enqueue(object : Callback<AvailabilityItemModel> {
                override fun onFailure(call: Call<AvailabilityItemModel>, t: Throwable) {
                    Log.d("API_NEW_AVAILABILITY", "FAIL")
                }

                override fun onResponse(
                    call: Call<AvailabilityItemModel>,
                    response: Response<AvailabilityItemModel>
                ) {
                    Log.d("RESPONSE_CODE", response.code().toString())
                    if (response.code() == 201) {
                        Log.d("API_NEW_AVAILABILITY", "Availability Item Created Successfully")
                    } else {
                        Log.d("API_NEW_AVAILABILITY", "Availability Item Not Created")
                    }
                }
            })
    }

    fun getAvailabilityItems(
        authorizationHeader: String?,
        userid: String?
    ): LiveData<MutableList<AvailabilityItemModel>?> {

        var items = MutableLiveData<MutableList<AvailabilityItemModel>>()

        apiInterface?.getAvailabilityItems(authorizationHeader, userid)
            ?.enqueue(object : Callback<MutableList<AvailabilityItemModel>> {
                override fun onFailure(
                    call: Call<MutableList<AvailabilityItemModel>>,
                    t: Throwable
                ) {
                    items.value = mutableListOf()
                    Log.d("GET_AVAILABILITY", "FAIL")
                }

                override fun onResponse(
                    call: Call<MutableList<AvailabilityItemModel>>,
                    response: Response<MutableList<AvailabilityItemModel>>
                ) {
                    val res: MutableList<AvailabilityItemModel>? = response.body()
                    //Log.d("POSTS", res.toString())
                    if (response.code() == 200 && res != null) {
                        items.value = res!!
                        Log.d("GET_AVAILABILITY", "Successful")
                    } else {
                        items.value = mutableListOf()
                        Log.d("GET_AVAILABILITY", "Error")
                    }
                }
            })
        return items
    }

    fun getBookRequests(
        authorizationHeader: String?,
        userid: String?
    ): LiveData<MutableList<BookRequestModel>?> {

        var items = MutableLiveData<MutableList<BookRequestModel>>()

        apiInterface?.getBookRequests(authorizationHeader, userid)
            ?.enqueue(object : Callback<MutableList<BookRequestModel>> {
                override fun onFailure(call: Call<MutableList<BookRequestModel>>, t: Throwable) {
                    items.value = mutableListOf()
                    Log.d("GET_REQUESTS", "FAIL")
                }

                override fun onResponse(
                    call: Call<MutableList<BookRequestModel>>,
                    response: Response<MutableList<BookRequestModel>>
                ) {
                    val res: MutableList<BookRequestModel>? = response.body()
                    //Log.d("POSTS", res.toString())
                    if (response.code() == 200 && res != null) {
                        items.value = res!!
                        Log.d("GET_REQUESTS", "Successful")
                    } else {
                        items.value = mutableListOf()
                        Log.d("GET_REQUESTS", "Error")
                    }
                }
            })
        return items
    }

    fun approveBookRequest(
        authorizationHeader: String?,
        bookResponseModel: BookResponseModel
    ) {

        apiInterface?.approveBookRequest(authorizationHeader, bookResponseModel)
            ?.enqueue(object : Callback<BookResponseModel> {
                override fun onFailure(call: Call<BookResponseModel>, t: Throwable) {
                    Log.d("BOOK_REQUEST", "FAIL")
                }

                override fun onResponse(
                    call: Call<BookResponseModel>,
                    response: Response<BookResponseModel>
                ) {
                    Log.d("RESPONSE_CODE", response.code().toString())
                    if (response.code() == 200) {
                        Log.d("BOOK_REQUEST", "successfully approved")
                    } else {
                        Log.d("BOOK_REQUEST", "not approved some error appear")
                    }
                }
            })
    }

    fun declineBookRequest(
        authorizationHeader: String?,
        bookResponseModel: BookResponseModel
    ) {

        apiInterface?.declineBookRequest(authorizationHeader, bookResponseModel)
            ?.enqueue(object : Callback<BookResponseModel> {
                override fun onFailure(call: Call<BookResponseModel>, t: Throwable) {
                    Log.d("BOOK_REQUEST", "FAIL")
                }

                override fun onResponse(
                    call: Call<BookResponseModel>,
                    response: Response<BookResponseModel>
                ) {
                    Log.d("RESPONSE_CODE", response.code().toString())
                    if (response.code() == 200) {
                        Log.d("BOOK_REQUEST", "successfully declined")
                    } else {
                        Log.d("BOOK_REQUEST", "not declined some error appear")
                    }
                }
            })
    }

    fun deletePost(
        authorizationHeader: String?,
        postID: String?
    ) {

        apiInterface?.deletePost(authorizationHeader, postID)
            ?.enqueue(object : Callback<PostModelResponse> {
                override fun onFailure(call: Call<PostModelResponse>, t: Throwable) {
                    Log.d("DELETE_POST", "FAIL")
                }

                override fun onResponse(
                    call: Call<PostModelResponse>,
                    response: Response<PostModelResponse>
                ) {
                    Log.d("RESPONSE_CODE", response.code().toString())
                    if (response.code() == 204) {
                        Log.d("DELETE_POST", "successfully deleted")
                    } else {
                        Log.d("DELETE_POST", "not deleted some error appear")
                    }
                }
            })
    }

    fun deleteAvailability(
        authorizationHeader: String?,
        availabilityID: String?
    ) {

        apiInterface?.deleteAvailability(authorizationHeader, availabilityID)
            ?.enqueue(object : Callback<AvailabilityItemModel> {
                override fun onFailure(call: Call<AvailabilityItemModel>, t: Throwable) {
                    Log.d("DELETE_AVAILABILITY", "FAIL")
                }

                override fun onResponse(
                    call: Call<AvailabilityItemModel>,
                    response: Response<AvailabilityItemModel>
                ) {
                    Log.d("RESPONSE_CODE", response.code().toString())
                    if (response.code() == 204) {
                        Log.d("DELETE_AVAILABILITY", "successfully deleted")
                    } else {
                        Log.d("DELETE_AVAILABILITY", "not deleted some error appear")
                    }
                }
            })
    }
}
