package com.example.spasdomuserapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.database.UserPreferences
import com.example.spasdomuserapp.databinding.FragmentLoginBinding
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.responses.AuthUser
import com.example.spasdomuserapp.ui.MainActivity
import com.example.spasdomuserapp.util.handleApiError
import com.example.spasdomuserapp.util.snackbar
import com.example.spasdomuserapp.util.startNewActivity
import com.example.spasdomuserapp.util.visible
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var userPreferences: UserPreferences
    private var fcmToken: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.progressbar.visible(false)

        userPreferences = UserPreferences(requireContext())

        userPreferences.fcmToken.asLiveData().observe(viewLifecycleOwner, {
            fcmToken = it
        })

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(it.value.access.token, it.value.refresh.token)
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.btnSignIn.setOnClickListener {
            login()
        }

    }

    private fun login() {
        binding.apply {
            val login = editTextLogin.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (login.isEmpty()) {
                editTextLogin.error = "Логин обязателен!"
                editTextLogin.requestFocus()
                return
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Пароль обязателен!"
                editTextPassword.requestFocus()
                return
            }

            if (fcmToken != null) {
                viewModel.login(AuthUser(login, password, fcmToken!!))
            } else {
                // this should never reach
                requireView().snackbar("Внутренняя ошибка. Попробуйте переустановить приложение")
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}