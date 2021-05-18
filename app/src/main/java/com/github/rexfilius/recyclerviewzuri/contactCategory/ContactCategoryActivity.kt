package com.github.rexfilius.recyclerviewzuri.contactCategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.github.rexfilius.recyclerviewzuri.contactList.ContactListActivity
import com.github.rexfilius.recyclerviewzuri.databinding.ActivityMainBinding

class ContactCategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
    }

    private fun setupAdapter() {
        val contactCategoryAdapter = ContactCategoryAdapter { category ->
            adapterOnClick(category)
        }

        binding.activityMainRecyclerView.adapter = contactCategoryAdapter
        binding.activityMainRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val category = ContactCategoryData.contactCategory
        contactCategoryAdapter.setupCategory(category)
    }

    private fun adapterOnClick(category: ContactCategoryModel) {
        val intent = Intent(this, ContactListActivity::class.java)
        intent.putExtra(CATEGORY_NAME_EXTRAS, category.name)
        startActivity(intent)
    }

}