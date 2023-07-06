package com.example.androiddigitalclock

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androiddigitalclock.databinding.ActivityMainBinding
import com.example.androiddigitalclock.databinding.DigitDisplayBinding
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.hourLeftDisplayManager.digitDisplayLiveData.observe(this) {map->
                setupLayoutWIthNewDigit(binding.layoutHourLeft,map)
        }
        viewModel.hourRightDisplayManager.digitDisplayLiveData.observe(this) {map->
                setupLayoutWIthNewDigit(binding.layoutHourRight,map)
        }
        viewModel.secondsLeftDisplayManager.digitDisplayLiveData.observe(this) {map->
            setupLayoutWIthNewDigit(binding.layoutSecondsLeft,map)
        }
        viewModel.secondsRightDisplayManager.digitDisplayLiveData.observe(this) {map->
            setupLayoutWIthNewDigit(binding.layoutSecondsRight,map)
        }

        viewModel.startCounting()
    }

    private fun setupLayoutWIthNewDigit(binding: DigitDisplayBinding,map: Map<Int,Int>) {
        styleCardView(binding.segmentTop.root,map[R.id.segmentTop]!!)
        styleCardView(binding.segmentTopLeft.root,map[R.id.segmentTopLeft]!!)
        styleCardView(binding.segmentTopRight.root,map[R.id.segmentTopRight]!!)
        styleCardView(binding.segmentMiddle.root,map[R.id.segmentMiddle]!!)
        styleCardView(binding.segmentBottomLeft.root,map[R.id.segmentBottomLeft]!!)
        styleCardView(binding.segmentBottomRight.root,map[R.id.segmentBottomRight]!!)
        styleCardView(binding.segmentBottom.root,map[R.id.segmentBottom]!!)
    }
    private fun styleCardView(materialCardView: MaterialCardView,@ColorRes colorRes:Int) {
        materialCardView.apply {
            val resolvedColor = ContextCompat.getColor(context,colorRes)
            setCardBackgroundColor(resolvedColor)
        }
    }
}
