package com.example.spasdomuserapp.ui.auth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentLoginBinding
import com.example.spasdomuserapp.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var preferences: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            //binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    /*lifecycleScope.launch {
                        viewModel.saveAccessTokens(
                            it.value.user.access_token!!,
                            it.value.user.refresh_token!!
                        )
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }*/
                }
                is Resource.Failure -> {Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()}/*handleApiError(it) { login() }*/
            }
        })


        binding.apply {
            btnSignIn.setOnClickListener {
                val login = editTextLogin.text.toString().trim()
                val password = editTextPassword.text.toString().trim()

                if (login.isEmpty()) {
                    editTextLogin.error = "Логин обязателен!"
                    editTextLogin.requestFocus()
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    editTextPassword.error = "Пароль обязателен!"
                    editTextPassword.requestFocus()
                    return@setOnClickListener
                }

                viewModel.login(login, password)
            }


        }


    }

    // Get token
    // [START log_reg_token]
    /*Firebase.messaging.token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w("fcm", "Fetching FCM registration token failed", task.exception)
            return@OnCompleteListener
        }

        // Get new FCM registration token
        val token = task.result

        val loginObject = LoginObject(login, password, token!!)
        Log.i("loginObject", loginObject.toString())


        Network.spasDom.tryLoginUser(loginObject)
            .enqueue(object: Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ) {
                    Toast.makeText(context, "Успешно залогинились", Toast.LENGTH_LONG).show()
                    Log.i("response", response.toString())

                    preferences = requireActivity().getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE)
                    preferences.edit()
                        .putBoolean(IS_LOGGED, true)
                        .apply()

                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            })*/
        // [END log_reg_token]

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}