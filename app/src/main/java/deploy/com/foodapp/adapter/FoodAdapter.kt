package deploy.com.foodapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import deploy.com.foodapp.R
import deploy.com.foodapp.activity.DetailActivity
import deploy.com.foodapp.databinding.ListFoodsBinding
import deploy.com.foodapp.response.ResponseFoodItem

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.AdapterViewModel>() {
    private var list = ArrayList<ResponseFoodItem>()
    fun setData(newList: List<ResponseFoodItem>?){
        if (newList == null) return
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AdapterViewModel( view : View) : RecyclerView.ViewHolder(view){
        val binding = ListFoodsBinding.bind(view)
        fun bind(data: ResponseFoodItem) {
            with(binding) {
                binding.tvNama.text = data.name
                binding.tvDeskripsi.text = data.desc
                Glide.with(itemView)
                    .load(data.image)
                    .into(ivPoto)
            }
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FOOD, data)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodAdapter.AdapterViewModel = AdapterViewModel(LayoutInflater.from(parent.context).inflate(R.layout.list_foods, parent, false))

    override fun onBindViewHolder(holder: FoodAdapter.AdapterViewModel, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size
}