package com.github.rexfilius.recyclerviewzuri.contactCategory

data class ContactCategoryModel(val name: String)

object ContactCategoryData {

    val contactCategory = listOf(
        ContactCategoryModel("Clients"),
        ContactCategoryModel("Club"),
        ContactCategoryModel("Friends"),
        ContactCategoryModel("Siblings"),
        ContactCategoryModel("Neighbours"),
        ContactCategoryModel("Coworkers"),
        ContactCategoryModel("Relatives"),
        ContactCategoryModel("Tutors"),
    )

}