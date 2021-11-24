package com.example.spasdomuserapp.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentLoginBinding
import com.example.spasdomuserapp.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,false)

        activity?.actionBar?.hide()

        binding.apply {
            btnSignIn.setOnClickListener {
                val login = editTextLogin.text.toString()
                val password = editTextPassword.text.toString()

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
                // TODO(logic for checking user)
                    Network.spasDom.tryLoginUser(login, password)
                        .enqueue(object: Callback<Boolean> {
                            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                                Toast.makeText(context, "Успешно залогинились", Toast.LENGTH_LONG).show()
                                Log.i("response", response.toString())
                                /*val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                                findNavController().navigate(action)*/
                            }
                        })

            }
        }

        return binding.root
    }
}