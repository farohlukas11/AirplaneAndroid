package com.faroh.airplaneandroid.view.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private var dataDestination: DestinationModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        dataDestination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DATA_DETAIL, DestinationModel::class.java)
        } else {
            intent.getParcelableExtra(DATA_DETAIL)
        }

        dataDestination?.let {
            detailBinding.apply {
                Glide.with(this@DetailActivity).load(it.imageUrl).into(detailBinding.ivDetail)
                tvDetailName.text = it.name
                tvDetailPlace.text = it.city
                tvRatingDetail.text = it.rating.toString()
            }
        }
    }

    companion object {
        const val DATA_DETAIL = "data_detail"
    }
}