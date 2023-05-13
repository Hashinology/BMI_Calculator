package com.hashinology.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hashinology.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 30
        binding.heightPicker.maxValue = 190

        binding.weightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        var height = binding.heightPicker.value
        var heightDouble = height.toDouble() / 100
        var weight = binding.weightPicker.value
        val bmi = weight.toDouble() / (heightDouble*heightDouble)

        binding.resultsTV.setText(String.format("Your BMI is: %.2f", bmi))
        binding.healthyTV.text = String.format("Considered@ %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String {
        if (bmi < 18.5)
            return "Under Weight"
        if(bmi < 25.0)
            return "Healthy"
        if(bmi < 30.0)
            return "Over Weight"
        return "Obese"
    }
}