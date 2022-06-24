package deploy.com.foodapp.retrofit

import deploy.com.foodapp.response.ResponseFood
import deploy.com.foodapp.response.ResponseFoodItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("foods")
    fun getFood(): Call<List<ResponseFoodItem>>
}