package com.example.exam3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.exam3.databinding.FragmentUserModifyBinding

class UserModifyFragment : Fragment() {

    private lateinit var binding:FragmentUserModifyBinding
    private lateinit var btnAdd: Button
    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etEmail: EditText
    private lateinit var user: User
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserModifyBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        btnAdd = binding.btnModify
        etFirstName = binding.etModifyName
        etSecondName = binding.etModifySecondName
        etEmail = binding.etModifyEmail
        btnAdd.setOnClickListener {
            modify()
        }
        setModel()
        setData()
    }

    private fun setModel() {
        user = arguments?.getParcelable<User>(UserFragment.ACCESS_MESSAGE)!!
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
            setFragmentResult(UserFragment.MODIFY_MESSAGE, bundleOf(UserFragment.ACCESS_MESSAGE to user))
            findNavController().navigateUp()
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