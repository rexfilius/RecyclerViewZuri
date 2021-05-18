package com.github.rexfilius.recyclerviewzuri.contactList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rexfilius.recyclerviewzuri.databinding.ContactListItemBinding

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {

    private val contactList = mutableListOf<ContactModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        return ContactListViewHolder(
            ContactListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setupContact(contact: List<ContactModel>) {
        this.contactList.addAll(contact)
        notifyDataSetChanged()
    }

    inner class ContactListViewHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactModel) {
            binding.contactListItemName.text = contact.name
            binding.contactListItemPhone.text = contact.number
        }

    }

}