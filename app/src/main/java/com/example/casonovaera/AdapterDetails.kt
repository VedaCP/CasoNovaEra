package com.example.casonovaera

import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casonovaera.databinding.ItemDetailBinding

class AdapterDetails: RecyclerView.Adapter<AdapterDetails.DetailsVH>() {

    private var listDetailNovaEra = listOf<NovaEraDetailEntity>()

    private val itemDetails = MutableLiveData<NovaEraDetailEntity>()

    fun update(list: List<NovaEraDetailEntity>) {
        listDetailNovaEra = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDetails.DetailsVH {
        return DetailsVH(ItemDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterDetails.DetailsVH, position: Int) {
        val mDetails = listDetailNovaEra[position]
    }

    override fun getItemCount(): Int = listDetailNovaEra.size

    inner class DetailsVH(private val binding: ItemDetailBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
                fun bind (novaEraDetailEntity: NovaEraDetailEntity){
                    Glide.with(binding.ivImageMovil)
                        .load(novaEraDetailEntity.image)
                        .into(binding.ivImageMovil)
                    binding.tvModel.text = novaEraDetailEntity.name
                    binding.tvPriceMovil.text = novaEraDetailEntity.price.toString()
                    binding.tvDescription.text = novaEraDetailEntity.description
                    binding.tvLastPrice.text = novaEraDetailEntity.lastPrice.toString()
                    if (novaEraDetailEntity.credit){
                        binding.tvCredit.text = "ACEPTA CRÉDITO"
                    }

                }

        override fun onClick(v: View?) {
            itemDetails.value = listDetailNovaEra[adapterPosition]
        }
    }

}