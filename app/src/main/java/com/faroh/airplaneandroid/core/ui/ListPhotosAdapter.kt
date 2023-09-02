package com.faroh.airplaneandroid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.databinding.ItemPhotosBinding

class ListPhotosAdapter(private val list: ArrayList<DestinationModel>) :
    RecyclerView.Adapter<ListPhotosAdapter.ListPhotosViewHolder>() {
    class ListPhotosViewHolder(var binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotosViewHolder {
        val binding = ItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListPhotosViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: ListPhotosViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list[position].imageUrl)
            .transform(CenterCrop(), RoundedCorners(18))
            .into(holder.binding.ivPhotos)
    }
}