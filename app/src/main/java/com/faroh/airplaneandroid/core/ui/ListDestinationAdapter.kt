package com.faroh.airplaneandroid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.databinding.ItemDestinationBinding

class ListDestinationAdapter(
    private val list: ArrayList<DestinationModel>,
    private val onClick: (DestinationModel) -> Unit
) : RecyclerView.Adapter<ListDestinationAdapter.ListDestinationViewHolder>() {
    class ListDestinationViewHolder(var binding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDestinationViewHolder {
        val binding =
            ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListDestinationViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListDestinationViewHolder, position: Int) {
        val data = list[position]

        val name = data.name
        val imageUrl = data.imageUrl
        val city = data.city
        val rating = data.rating

        Glide.with(holder.itemView.context).load(imageUrl)
            .transform(CenterCrop(), RoundedCorners(18))
            .into(holder.binding.ivDestination)

        holder.binding.apply {
            tvNameDestination.text = name
            tvPlaceDestination.text = city
            tvRating.text = rating.toString()
        }

        holder.itemView.setOnClickListener {
            onClick(list[holder.adapterPosition])
        }
    }
}