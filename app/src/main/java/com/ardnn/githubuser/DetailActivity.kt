package com.ardnn.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardnn.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var user: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel
        setDataToWidgets()

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setDataToWidgets() {
        binding.ivAva.setImageResource(user.avatar)
        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.tvFollowers.text = user.follower.toString()
        binding.tvFollowing.text = user.following.toString()
        binding.tvRepositories.text = user.repository.toString()
        binding.tvLocation.text = user.location
        binding.tvCompany.text = user.company
    }
}