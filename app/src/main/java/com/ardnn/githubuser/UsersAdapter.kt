package com.ardnn.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.githubuser.databinding.ItemRvUsersBinding

class UsersAdapter(
    private val userList: ArrayList<UserModel>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_users, parent, false)

        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View, clickListener: ClickListener)
        : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRvUsersBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                clickListener.itemClicked(userList[adapterPosition])
            }
        }

        internal fun onBind(user: UserModel) {
            with(binding) {
                ivAva.setImageResource(user.avatar)
                tvName.text = user.name
                tvUsername.text = user.username
                tvLocation.text = user.location
            }
        }
    }

    interface ClickListener {
        fun itemClicked(user: UserModel)
    }

}