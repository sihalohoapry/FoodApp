package deploy.com.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import deploy.com.foodapp.response.ResponseFood
import deploy.com.foodapp.response.ResponseFoodItem
import deploy.com.foodapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel : ViewModel() {
    private val _listFood = MutableLiveData<List<ResponseFoodItem>>()
    val listFood: LiveData<List<ResponseFoodItem>> = _listFood
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    init {
        getListFood()
    }

    private fun getListFood(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFood()
        client.enqueue(object : Callback<List<ResponseFoodItem>>{
            override fun onResponse(
                call: Call<List<ResponseFoodItem>>,
                response: Response<List<ResponseFoodItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _listFood.value = response.body()
                }else{
                    Log.e("HomeViewModel", "OnFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseFoodItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("HomeViewModel", "OnFailure: ${t.message.toString()}")
            }
        })
    }

}