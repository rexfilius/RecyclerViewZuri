package com.github.rexfilius.recyclerviewzuri.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.rexfilius.recyclerviewzuri.contactCategory.ContactCategoryActivity
import com.github.rexfilius.recyclerviewzuri.database.UserDetail
import com.github.rexfilius.recyclerviewzuri.database.UserDetailRepository
import com.github.rexfilius.recyclerviewzuri.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: LoginBinding
    private lateinit var userDetailRepository: UserDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDetailRepository = UserDetailRepository(this)

        binding.loginButton.setOnClickListener { confirmUserDetails() }
    }

    private fun confirmUserDetails() {
        val emailText = binding.loginEmail.text
        val passwordText = binding.loginPassword.text

        if (emailText.isNullOrEmpty() || passwordText.isNullOrEmpty()) {
            toast("Enter your details!")
        } else {
            val userDetail = UserDetail(emailText.toString(), passwordText.toString())

            if (userDetail === userDetailRepository.findUserByEmail(userDetail)) {
                toast("Login Successful")
                goToContactCategoryActivity()
            } else {
                toast("Details not found in records!")
            }
        }
    }

    private fun goToContactCategoryActivity() {
        val intent = Intent(this, ContactCategoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}