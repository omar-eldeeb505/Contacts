package com.example.contacts

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contacts.Utili.Constants

import com.example.contacts.databinding.ActivityDetailsBinding
import com.example.contacts.model.Contact



class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SetActivityViews()
    }

    private fun SetActivityViews() {
        val contact = IntentCompat.getParcelableExtra(intent,Constants.CONTACT,Contact::class.java)
        contact?.let {contact ->
            binding.nameView.text=contact.name
            binding.phoneView.text=contact.phone
            binding.descriptionView.text=contact.description
        }
    }
}

