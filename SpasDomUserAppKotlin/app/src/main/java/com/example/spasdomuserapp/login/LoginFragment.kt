package com.example.spasdomuserapp.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentLoginBinding
import com.example.spasdomuserapp.network.LoginObject
import com.example.spasdomuserapp.network.Network
import com.example.spasdomuserapp.util.SHARED_PREF_IS_LOGGED
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

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

                // Get token
                // [START log_reg_token]
                Firebase.messaging.token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("fcm", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result

                    val loginObject = LoginObject(login, password, token!!)
                    Log.i("loginObject", loginObject.toString())

                    // TODO(logic for checking user)
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

                                val sharedPreference = context!!.getSharedPreferences(SHARED_PREF_IS_LOGGED,
                                    Context.MODE_PRIVATE)
                                var editor = sharedPreference.edit()
                                editor.putBoolean("isLogged",true)
                                editor.commit()

                                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                                findNavController().navigate(action)
                            }
                        })
                // [END log_reg_token]

                })
            }
        }

        return binding.root
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