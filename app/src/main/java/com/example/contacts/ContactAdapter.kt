package com.example.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemContactBinding
import com.example.contacts.model.Contact


class ContactAdapter(val contactslist:MutableList<Contact>):RecyclerView.Adapter<ContactAdapter.viewHolder>(){
   class viewHolder(val itemContactBinding: ItemContactBinding):RecyclerView.ViewHolder(itemContactBinding.root) {
       fun bind(contact: Contact){
           itemContactBinding.profileName.text=contact.name
           itemContactBinding.profilePhone.text=contact.phone
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int = contactslist.size


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val contact = contactslist[position]
        holder.bind(contact)
        holder.itemContactBinding.root.setOnClickListener{
           onItemClicked?.onClick(contact)
        }

    }
    fun interface  OnItemClicked{
        fun onClick(contact: Contact)
    }
    var onItemClicked:OnItemClicked?=null
}

