package com.faroh.airplaneandroid.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)


    }

    companion object {
        const val DATA_DETAIL = "data_detail"
    }
}