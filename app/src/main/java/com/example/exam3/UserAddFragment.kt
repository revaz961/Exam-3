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
import com.example.exam3.databinding.FragmentUserAddBinding

class UserAddFragment : Fragment() {

    private lateinit var binding:FragmentUserAddBinding
    private lateinit var btnAdd: Button
    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etEmail: EditText
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAddBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }


    private fun init(){
        btnAdd = binding.btnAddUser
        etFirstName = binding.etName
        etSecondName = binding.etSecondName
        etEmail = binding.etEmail
        btnAdd.setOnClickListener{
            addUser()
        }
    }

    private fun addUser(){
        if(validateUser()){
            val user = User(etFirstName.text.trim().toString(),
                etSecondName.text.trim().toString(),
                etEmail.text.trim().toString())
            setFragmentResult(UserFragment.ADD_MESSAGE, bundleOf(UserFragment.ACCESS_MESSAGE to user))
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