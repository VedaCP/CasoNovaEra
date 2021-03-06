package com.example.casonovaera

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casonovaera.databinding.ItemNovaBinding

class NovaEraAdapter: RecyclerView.Adapter<NovaEraAdapter.ProductsVH>() {

    private var listAdapterProducts = listOf<NovaEraEntity>()

    private var productsItem = MutableLiveData<NovaEraEntity>()

    fun productsItem(): LiveData<NovaEraEntity> = productsItem

    fun update(list: List<NovaEraEntity>){
        listAdapterProducts = list
        notifyDataSetChanged()
    }

    inner class ProductsVH(private val binding: ItemNovaBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
                fun bind(novaEraEntity: NovaEraEntity){
                    Glide.with(binding.ivImage).load(novaEraEntity.image)
                        .into(binding.ivImage)
                    binding.tvName.text = novaEraEntity.name
                    binding.tvPrice.text = novaEraEntity.price.toString()

                    itemView.setOnClickListener(this)
                }

        override fun onClick(v: View?) {
            productsItem.value = listAdapterProducts[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsVH {
        return ProductsVH(ItemNovaBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductsVH, position: Int) {
        val productsDC = listAdapterProducts[position]
        holder.bind(productsDC)
    }

    override fun getItemCount(): Int = listAdapterProducts.size
}