package com.faroh.airplaneandroid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.databinding.ItemTransactionBinding

class ListTransactionAdapter(private val list: ArrayList<TransactionModel>) :
    RecyclerView.Adapter<ListTransactionAdapter.ListTransactionViewHolder>() {
    class ListTransactionViewHolder(var binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListTransactionViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListTransactionViewHolder, position: Int) {
        val data = list[position]

        Glide.with(holder.itemView.context).load(data.destination?.imageUrl)
            .transform(CenterCrop(), RoundedCorners(18))
            .into(holder.binding.ivDestinationBook)

        holder.binding.apply {
            tvNameBook.text = data.destination?.name
            tvCityBook.text = data.destination?.city
            tvRatingBook.text = data.destination?.rating.toString()
            tvPriceBook.text = Formatter.rupiahFormatter(data.price)
            tvGrandTotal.text = Formatter.rupiahFormatter(data.grandTotal)
            tvSeat.text = data.selectedSeats
        }
    }
}