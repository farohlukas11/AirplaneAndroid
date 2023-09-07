package com.faroh.airplaneandroid.view.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.databinding.FragmentSettingsBinding
import com.faroh.airplaneandroid.view.signin.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sign

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var settingsBinding: FragmentSettingsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsBinding = FragmentSettingsBinding.inflate(layoutInflater)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsBinding.btnSignOut.setOnClickListener {
            settingsViewModel.apply {
                signOut()
                setUserState(false)
            }

            val i = Intent(requireContext(), SignInActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
    }
}