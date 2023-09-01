package com.faroh.airplaneandroid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.databinding.ItemNewDestinationBinding

class ListNewDestinationAdapter(
    private val list: ArrayList<DestinationModel>,
    private val onClick: (DestinationModel) -> Unit
) : RecyclerView.Adapter<ListNewDestinationAdapter.ListNewDestinationViewHolder>() {
    class ListNewDestinationViewHolder(var binding: ItemNewDestinationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListNewDestinationViewHolder {
        val binding =
            ItemNewDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListNewDestinationViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListNewDestinationViewHolder, position: Int) {
        val data = list[position]

        val name = data.name
        val imageUrl = data.imageUrl
        val city = data.city
        val rating = data.rating

        Glide.with(holder.itemView.context).load(imageUrl)
            .transform(CenterCrop(), RoundedCorners(18))
            .into(holder.binding.ivNewDestination)

        holder.binding.apply {
            tvNameNewDestination.text = name
            tvPlaceNewDestination.text = city
            tvRating.text = rating.toString()
        }

        holder.itemView.setOnClickListener {
            onClick(list[holder.adapterPosition])
        }
    }
}