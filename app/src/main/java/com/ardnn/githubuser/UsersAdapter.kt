package com.ardnn.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.githubuser.databinding.ItemRvUsersBinding

class UsersAdapter(
    private val userList: MutableList<UserModel>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(), Filterable {
    private val userListFull: MutableList<UserModel> = mutableListOf()

    init {
        userListFull.addAll(userList)
    }

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


    // search operations =========================================
    override fun getFilter(): Filter {
        return searchFilter
    }

    private val searchFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<UserModel> = mutableListOf()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(userListFull)
            } else {
                val searchedWords = constraint.toString().trim()
                for (user in userListFull) {
                    val startsWithName = user.name.startsWith(searchedWords, true)
                    val startsWithUsername = user.username.startsWith(searchedWords, true)
                    val startsWithLocation = user.location.startsWith(searchedWords, true)
                    if (startsWithName || startsWithUsername || startsWithLocation) {
                        filteredList.add(user)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            userList.clear()
            userList.addAll(results?.values as MutableList<UserModel>)
            notifyDataSetChanged()
        }

    }


}