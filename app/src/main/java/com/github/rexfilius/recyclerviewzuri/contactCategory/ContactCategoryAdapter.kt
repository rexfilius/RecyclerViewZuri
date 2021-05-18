package com.github.rexfilius.recyclerviewzuri.contactCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rexfilius.recyclerviewzuri.databinding.ContactCategoryItemBinding

class ContactCategoryAdapter(private val onClick: (ContactCategoryModel) -> Unit) :
    RecyclerView.Adapter<ContactCategoryAdapter.ContactCategoryViewHolder>() {

    private val categoryList = mutableListOf<ContactCategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCategoryViewHolder {
        return ContactCategoryViewHolder(
            ContactCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactCategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category, onClick)
    }

    override fun getItemCount(): Int = categoryList.size

    fun setupCategory(category: List<ContactCategoryModel>) {
        this.categoryList.addAll(category)
        notifyDataSetChanged()
    }

    inner class ContactCategoryViewHolder(private val binding: ContactCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            category: ContactCategoryModel,
            onClick: (ContactCategoryModel) -> Unit
        ) {
            binding.categoryItemTextView.text = category.name
            binding.root.setOnClickListener { onClick(category) }
        }

    }

}

// Intent Extras
const val CATEGORY_NAME_EXTRAS = "category name"

