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
    private lateinit var user: User
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_modify)
        init()
    }

    private fun init() {
        btnAdd = findViewById(R.id.btnModify)
        etFirstName = findViewById(R.id.etModifyName)
        etSecondName = findViewById(R.id.etModifySecondName)
        etEmail = findViewById(R.id.etModifyEmail)
        btnAdd.setOnClickListener {
            modify()
        }
        setModel()
        setData()
    }

    private fun setModel() {
        user = intent.getParcelableExtra<User>(UserActivity.MODIFY_MESSAGE) as User
    }

    private fun setData() {
        etFirstName.setText(user.name)
        etSecondName.setText(user.secondName)
        etEmail.setText(user.email)
    }

    private fun modify() {
        if (validateUser()) {
            user.name = etFirstName.text.trim().toString()
            user.secondName = etSecondName.text.trim().toString()
            user.email = etEmail.text.trim().toString()
            val intent = Intent()
            intent.putExtra(UserActivity.MODIFY_MESSAGE, user)
            setResult(RESULT_OK, intent)
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