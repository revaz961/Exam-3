package com.example.exam3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam3.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    companion object {
        const val REQUEST_ACCESS_TYPE = 1
        const val REQUEST_MODIFY_TYPE = 2
        const val ADD_MESSAGE = "add"
        const val MODIFY_MESSAGE = "modify"
        const val ACCESS_MESSAGE = "user"
    }

    private val users = mutableListOf<User>()
    private lateinit var adapter: UserRecyclerAdapter
    private lateinit var rvUsers: RecyclerView
    private lateinit var btnAdd: ImageView
    private lateinit var btnClear: ImageView
    private var modifyPosition = -1
    private lateinit var binding: FragmentUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setData()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        init()
        return binding.root
    }


    private fun init() {
        rvUsers = binding.rvUsers
        btnAdd = binding.btnAdd
        btnClear = binding.btnClear

        setFragmentResultListener(ADD_MESSAGE) { key, bundle ->
            val user = bundle.getParcelable<User>(ACCESS_MESSAGE)!!
            users.add(user)
            adapter.notifyItemInserted(users.size - 1)
        }

        setFragmentResultListener(MODIFY_MESSAGE){key, bundle ->
            if(modifyPosition != -1){
                val user = bundle.getParcelable<User>(ACCESS_MESSAGE)!!
                users[modifyPosition] = user
                adapter.notifyItemChanged(modifyPosition)
                modifyPosition = -1
            }
        }


        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_userAddFragment)
        }

        btnClear.setOnClickListener {
            if (users.isNotEmpty()) {
                adapter.notifyItemRangeRemoved(0, users.size)
                users.clear()
            }
        }

        adapter = UserRecyclerAdapter(users,
            {
                adapter.notifyItemRemoved(it)
                users.removeAt(it)
            }) {
            modifyPosition = it
            val bundle = bundleOf(ACCESS_MESSAGE to users[it])
            findNavController().navigate(R.id.action_userFragment_to_userModifyFragment, bundle)
        }

        rvUsers.layoutManager = GridLayoutManager(context, 2)
        rvUsers.adapter = adapter
    }

    private fun setData() {
        users.addAll(
            listOf(
                User("Revaz", "Giorgadze", "revaz961@gmail.com"),
                User("Nika", "Mamukelashvili", "Nika961@gmail.com"),
                User("Giorgi", "Odishvili", "Giorgi961@gmail.com"),
                User("Mari", "Samniashvili", "Mari961@gmail.com"),
                User("Nino", "Mamacashvili", "Nino961@gmail.com"),
                User("Levani", "Zakeridze", "Levani961@gmail.com"),
                User("Robi", "Mchedlishvili", "Robi961@gmail.com"),
                User("Natia", "Gagnidze", "Natia961@gmail.com"),
            )
        )
    }
}