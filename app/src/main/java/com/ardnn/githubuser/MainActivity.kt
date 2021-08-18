package com.ardnn.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardnn.githubuser.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.IOError
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), UsersAdapter.ClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersData: UsersData
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get user data from githubuser.json
        usersData = UsersData(this)
        val userList = usersData.userList

        // setup recyclerview
        usersAdapter = UsersAdapter(userList, this)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.setHasFixedSize(true)

        // set action bar
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun itemClicked(user: UserModel) {
        Toast.makeText(this, "You clicked ${user.name}", Toast.LENGTH_SHORT).show()
    }
}