package deploy.com.foodapp.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseFood(

	@field:SerializedName("ResponseFood")
	val responseFood: List<ResponseFoodItem>
)

@Parcelize
data class ResponseFoodItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("desc")
	val desc: String
) : Parcelable
