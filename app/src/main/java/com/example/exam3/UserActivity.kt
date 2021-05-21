package com.example.exam3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_ACCESS_TYPE = 1
        const val REQUEST_MODIFY_TYPE = 2
        const val ACCESS_MESSAGE = "user"
        const val ACCESS_MESSAGE_MODIFY = "modify"
    }

    private val users = mutableListOf<User>()
    private lateinit var adapter: UserRecyclerAdapter
    private lateinit var rvUsers: RecyclerView
    private lateinit var btnAdd: ImageView
    private lateinit var modifyPosition: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ACCESS_TYPE) {
            if (resultCode == RESULT_OK) {
                val user = data?.getParcelableExtra<User>(ACCESS_MESSAGE) as User
                users.add(user)
                adapter.notifyDataSetChanged()
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
        if (requestCode == REQUEST_MODIFY_TYPE) {
            if (resultCode == RESULT_OK) {
                val user = data?.getParcelableExtra<User>(ACCESS_MESSAGE) as User
                users.add(user)
                adapter.notifyDataSetChanged()
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun init(){
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivityForResult(intent,REQUEST_ACCESS_TYPE)
        }

        rvUsers = findViewById(R.id.rvUsers)
        setData()
        adapter = UserRecyclerAdapter(users,{
            adapter.notifyItemRemoved(it)
            users.removeAt(it)
        }){
//            val intent = Intent(this, AddUserActivity::class.java)
//            intent.putExtra(REQUEST_MODIFY_TYPE,users[it])
//            startActivityForResult(intent, REQUEST_MODIFY_TYPE)
        }
        rvUsers.layoutManager = GridLayoutManager(this,2)
        rvUsers.adapter = adapter
    }

    private fun setData(){
        users.addAll(listOf(
            User("Revaz","Giorgadze","revaz961@gmail.com"),
            User("Nika","Mamukelashvili","Nika961@gmail.com"),
            User("Giorgi","Odishvili","Giorgi961@gmail.com"),
            User("Mari","Samniashvili","Mari961@gmail.com"),
            User("Nino","Mamacashvili","Nino961@gmail.com"),
            User("Levani","Zakeridze","Levani961@gmail.com"),
            User("Robi","Mchedlishvili","Robi961@gmail.com"),
            User("Natia","Gagnidze","Natia961@gmail.com"),
        ))
    }
}