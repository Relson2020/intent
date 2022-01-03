package com.example.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.dialButton.setOnClickListener{
            val number = "0976374837"
            val dial = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $number" ))
            startActivity(dial)

        }

        binding.mapButton.setOnClickListener{
            val map = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"))
            startActivity(map)
        }

        binding.browserButton.setOnClickListener{
            val browser = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("sonu.prajapati@relinns.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                // You can also attach multiple items by passing an ArrayList of Uris
            }
            startActivity(browser)
        }
        val result  = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.imageView.setImageURI(it)
            })

        binding.imageButton.setOnClickListener{

            result.launch("image/*")
        }

        binding.textButton.setOnClickListener {
            val text = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT,"hey there")
                type = "text/plain"
            }
            startActivity(text)
        }

    }
}