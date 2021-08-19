package com.ardnn.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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

        // if user is searching
        val searchItem = menu?.findItem(R.id.toolbar_item_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                usersAdapter.filter.filter(newText)
                return true
            }

        })

        return true
    }

    override fun itemClicked(user: UserModel) {
        val goToDetail = Intent(this@MainActivity, DetailActivity::class.java)
        goToDetail.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(goToDetail)
    }
}