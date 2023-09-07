package com.faroh.airplaneandroid.view.saldo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.FragmentSaldoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaldoFragment : Fragment() {
    private lateinit var saldoBinding: FragmentSaldoBinding
    private val saldoViewModel: SaldoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        saldoBinding = FragmentSaldoBinding.inflate(layoutInflater)
        return saldoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saldoViewModel.getUserToken().observe(requireActivity()) { token ->
            token?.let {
                saldoViewModel.getUserById(it).observe(requireActivity()) { response ->
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {

                            val data = response.data

                            data?.let { user ->
                                saldoBinding.apply {
                                    tvEmailUser.text = user.email
                                    tvNameUser.text = user.name
                                    tvHobbyUser.text = user.hobby
                                    tvBalanceUser.text = Formatter.rupiahFormatter(user.balance)
                                }
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
    }
}