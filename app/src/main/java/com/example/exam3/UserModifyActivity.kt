package com.example.exam3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserModifyActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etEmail: EditText
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        init()
    }

    private fun init(){
        btnAdd = findViewById(R.id.btnAddUser)
        etFirstName = findViewById(R.id.etName)
        etSecondName = findViewById(R.id.etSecondName)
        etEmail = findViewById(R.id.etEmail)
        addUser()
    }

    private fun addUser(){
        if(validateUser()){
            val user = User(etFirstName.text.trim().toString(),
                etSecondName.text.trim().toString(),
                etEmail.text.trim().toString())
            val intent = Intent()
            intent.putExtra(UserActivity.ACCESS_MESSAGE,user)
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    private fun validateUser(): Boolean {
        var valid = true
        etFirstName.setBackgroundResource(R.color.green)
        etSecondName.setBackgroundResource(R.color.green)
        etEmail.setBackgroundResource(R.color.green)

        if (etFirstName.text.trim().isEmpty()) {
            etFirstName.setBackgroundResource(R.color.red)
            valid = false
        }
        if (etSecondName.text.trim().isEmpty()) {
            etSecondName.setBackgroundResource(R.color.red)
            valid = false
        }
        if (!etEmail.text.trim().matches(emailPattern.toRegex())) {
            etEmail.setBackgroundResource(R.color.red)
            valid = false
        }

        return valid
    }
}