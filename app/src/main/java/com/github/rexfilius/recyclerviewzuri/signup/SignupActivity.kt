package com.github.rexfilius.recyclerviewzuri.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.rexfilius.recyclerviewzuri.R
import com.github.rexfilius.recyclerviewzuri.database.UserDetail
import com.github.rexfilius.recyclerviewzuri.database.UserDetailRepository
import com.github.rexfilius.recyclerviewzuri.databinding.SignupBinding
import com.github.rexfilius.recyclerviewzuri.login.LoginActivity

class SignupActivity : AppCompatActivity() {

    lateinit var binding: SignupBinding
    private lateinit var userDetailRepository: UserDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDetailRepository = UserDetailRepository(this)

        binding.signupButton.setOnClickListener { saveUserDetails() }
        binding.signupTextBottom.setOnClickListener { gotoLoginActivity() }
    }

    private fun saveUserDetails() {
        val emailText = binding.signupEmail.text
        val passwordText = binding.signupPassword.text

        if (emailText.isNullOrEmpty() || passwordText.isNullOrEmpty()) {
            toast("Enter your details!")
        } else {
            val userDetail = UserDetail(emailText.toString(), passwordText.toString())

            if(userDetail === userDetailRepository.findUserByEmail(userDetail)) {
                toast("You've signed up already")
            } else {
                userDetailRepository.insertUserDetails(userDetail)
                toast("Sign-up successful")
                gotoLoginActivity()
            }
        }
    }

    private fun gotoLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}