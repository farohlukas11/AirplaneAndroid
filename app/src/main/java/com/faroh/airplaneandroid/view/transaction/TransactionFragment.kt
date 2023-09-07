package com.faroh.airplaneandroid.view.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.ui.ListTransactionAdapter
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private lateinit var transactionBinding: FragmentTransactionBinding
    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        transactionBinding = FragmentTransactionBinding.inflate(layoutInflater)
        return transactionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionViewModel.getAllTransaction().observe(requireActivity()) { response ->
            when (response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val data = response.data

                    data?.let {
                        transactionBinding.rvTransaction.layoutManager =
                            LinearLayoutManager(requireContext())

                        val listTransaction = ArrayList<TransactionModel>()
                        for (transaction in it) {
                            listTransaction.add(transaction)
                        }

                        val listTransactionAdapter = ListTransactionAdapter(listTransaction)
                        transactionBinding.rvTransaction.adapter = listTransactionAdapter
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