package deploy.com.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import deploy.com.foodapp.R
import deploy.com.foodapp.databinding.ActivityDetailBinding
import deploy.com.foodapp.response.ResponseFoodItem

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FOOD = "extra_food"
    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivBack.setOnClickListener {
            super.onBackPressed()
        }
        val data = intent.getParcelableExtra<ResponseFoodItem?>(EXTRA_FOOD)
        binding.apply {
            tvNama.text = data?.name
            tvResep.text = data?.desc
            Glide.with(this@DetailActivity)
                .load(data?.image)
                .into(ivFoto)
        }

    }
}