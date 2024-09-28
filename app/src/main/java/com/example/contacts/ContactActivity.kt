package com.example.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.Utili.Constants
import com.example.contacts.databinding.ActivityContactsBinding
import com.example.contacts.model.Contact

class ContactActivity :AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    lateinit var name:String
    lateinit var phone: String
    lateinit var description: String
    val contactlist= mutableListOf<Contact>()
    var adapter= ContactAdapter(contactlist)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        SaveBtnClick()

    }

    private fun initRV() {
        binding.rvContact.adapter=adapter
        adapter.onItemClicked =
            ContactAdapter.OnItemClicked { contact -> navToDetailsActivity(contact) }
    }

    private fun navToDetailsActivity(contact: Contact) {
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra(Constants.CONTACT,contact)
        startActivity(intent)
    }

    private fun SaveBtnClick() {
        binding.saveBtn.setOnClickListener {
            if (!ValidField()) {
                return@setOnClickListener
            }
            name=binding.nameEdit.text?.trim().toString()
            phone=binding.phoneEdit.text?.trim().toString()
            description=binding.desEdit.text?.trim().toString()

            val contact = Contact(name=name,phone=phone,description=description)
            contactlist.add(contact)
            adapter.notifyItemInserted(contactlist.size-1)
            binding.nameEdit.text?.clear()
            binding.phoneEdit.text?.clear()
            binding.desEdit.text?.clear()

        }
    }

    private fun ValidField(): Boolean {
        name=binding.nameEdit.text?.trim().toString()
        phone=binding.phoneEdit.text?.trim().toString()

        binding.nameTitle.error=ValidName(name)
        binding.phoneTitle.error=ValidPhone(phone)

        return ValidName(name)==null&&ValidPhone(phone)==null
    }

    fun ValidName(name: String): String? {
        if (name.isEmpty()) {
            return "Required"
        }
        if (name.length < 3) {
            return "Name Must be greater than 3"
        }
          return null
    }
    fun ValidPhone(phone: String): String? {
        if (phone.isEmpty()) {
            return "Required"
        }
        if (phone.length < 11) {
            return "Phone Must be greater than 3"
        }
        val phonepatterns="^[0-9]+$"
        if (!phone.matches(phonepatterns.toRegex())){
            return "phone can only contain digits"
        }
        return null
    }
    }


