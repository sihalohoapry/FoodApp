package deploy.com.foodapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService() : ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://18.221.132.15:7000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}