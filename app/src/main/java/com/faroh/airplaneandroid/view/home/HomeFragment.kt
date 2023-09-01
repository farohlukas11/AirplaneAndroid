package com.faroh.airplaneandroid.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.ui.ListDestinationAdapter
import com.faroh.airplaneandroid.core.ui.ListNewDestinationAdapter
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.FragmentHomeBinding
import com.faroh.airplaneandroid.view.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getUserToken().observe(requireActivity()) {
            it?.let { idUser ->
                homeViewModel.getUserById(idUser).observe(requireActivity()) { response ->
                    when (response) {
                        is Resource.Loading -> {}

                        is Resource.Success -> {
                            val data = response.data

                            data?.let { user ->
                                homeBinding.tvHowdyName.text =
                                    getString(R.string.howdy_name, user.name)
                            }
                        }

                        is Resource.Error -> {
                            Toast(
                                requireContext()
                            ).showCustomToast(
                                true,
                                response.message.toString(),
                                requireActivity()
                            )
                        }

                    }
                }
            }
        }

        homeViewModel.getAllDestination().observe(requireActivity()) { response ->
            when (response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val data = response.data

                    data?.let {
                        homeBinding.rvDestination.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )

                        homeBinding.rvNewDestination.layoutManager =
                            LinearLayoutManager(requireContext())

                        val listDestination = arrayListOf<DestinationModel>()
                        for (destination in it) {
                            listDestination.add(destination)
                        }

                        val listDestinationAdapter =
                            ListDestinationAdapter(listDestination) { destination ->
                                val intentDetail =
                                    Intent(requireActivity(), DetailActivity::class.java)
                                intentDetail.putExtra(DetailActivity.DATA_DETAIL, destination)
                                startActivity(intentDetail)
                            }
                        homeBinding.rvDestination.adapter = listDestinationAdapter

                        val listNewDestination = arrayListOf<DestinationModel>()
                        for (destination in it.filter { d -> d.price!! <= 3000000 }) {
                            listNewDestination.add(destination)
                        }

                        val listNewDestinationAdapter =
                            ListNewDestinationAdapter(listNewDestination) { newDestination ->
                                val intentDetail =
                                    Intent(requireActivity(), DetailActivity::class.java)
                                intentDetail.putExtra(DetailActivity.DATA_DETAIL, newDestination)
                                startActivity(intentDetail)
                            }
                        homeBinding.rvNewDestination.adapter = listNewDestinationAdapter
                    }
                }

                is Resource.Error -> Toast(
                    requireContext()
                ).showCustomToast(
                    true,
                    response.message.toString(),
                    requireActivity()
                )
            }
        }
    }
}