package com.github.rexfilius.recyclerviewzuri.contactList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.recyclerviewzuri.contactCategory.CATEGORY_NAME_EXTRAS
import com.github.rexfilius.recyclerviewzuri.databinding.ContactListBinding
import com.github.rexfilius.recyclerviewzuri.databinding.DialogAddContactBinding

class ContactListActivity : AppCompatActivity() {

    lateinit var binding: ContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Appbar title
        title = intent.getStringExtra(CATEGORY_NAME_EXTRAS)

        setupAdapterAndDialog()
    }

    private fun setupAdapterAndDialog() {
        val contactListAdapter = ContactListAdapter()
        binding.contactListRecyclerView.adapter = contactListAdapter
        binding.contactListRecyclerView.layoutManager = LinearLayoutManager(this)

        val alertDialogBuilder = AlertDialog.Builder(this)
        val dialogBinding = DialogAddContactBinding.inflate(LayoutInflater.from(this))
        alertDialogBuilder.setView(dialogBinding.root)

        val alertDialog = alertDialogBuilder.create()
        binding.contactListFAB.setOnClickListener { alertDialog.show() }

        dialogBinding.dialogContactSave.setOnClickListener {
            val contact = ContactModel(
                dialogBinding.dialogContactName.text.toString(),
                dialogBinding.dialogContactPhone.text.toString()
            )

            val contacts = mutableListOf(contact)
            contactListAdapter.setupContact(contacts)
            alertDialog.dismiss()
        }
    }
}