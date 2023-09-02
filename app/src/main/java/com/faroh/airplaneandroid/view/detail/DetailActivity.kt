package com.faroh.airplaneandroid.view.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.ui.ListPhotosAdapter
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
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
                tvPriceDetail.text = Formatter.rupiahFormatter(it.price!!)
                tvRatingDetail.text = it.rating.toString()
            }
        }

        detailViewModel.getAllDestination().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val data = response.data

                    data?.let {
                        detailBinding.rvPhotos.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

                        val listPhotos = arrayListOf<DestinationModel>()
                        for (photo in it) {
                            listPhotos.add(photo)
                        }

                        val listPhotosAdapter = ListPhotosAdapter(listPhotos)
                        detailBinding.rvPhotos.adapter = listPhotosAdapter
                    }
                }

                is Resource.Error -> {}
            }
        }

        detailBinding.btnBookNow.setOnClickListener {

        }
    }

    companion object {
        const val DATA_DETAIL = "data_detail"
    }
}