package com.github.guilhe.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.guilhe.stylinglint.sample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Color.parseColor("#FFFFFF")
        resources.getColor(R.color.colorPrimary, theme)
        getColor(R.color.colorPrimary)
        ContextCompat.getColor(this, R.color.colorPrimary)

        resources.getColor(android.R.color.transparent, theme)
        getColor(android.R.color.transparent)
        ContextCompat.getColor(this, android.R.color.transparent)
    }
}