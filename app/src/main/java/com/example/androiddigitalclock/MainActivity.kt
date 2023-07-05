package com.example.androiddigitalclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androiddigitalclock.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.onButtonClicked()
        }

        viewModel.segmentLiveData.observe(this) { colorRes->
            binding.segment.root.apply {
                val resolvedColor = ContextCompat.getColor(context,colorRes)
                setCardBackgroundColor(resolvedColor)
            }
        }
    }
}
