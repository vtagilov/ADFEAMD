package com.example.tagilov_lr1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        for (seekBarID in intArrayOf(R.id.RseekBar, R.id.GseekBar, R.id.BseekBar)) {
            findViewById<SeekBar>(seekBarID).setOnSeekBarChangeListener(this)
        }
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "red: " + red + "\nblue: " + blue + "\ngreen: " + green
        updateColors()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        if (seekBar != null) {
            val value = (seekBar.progress * 2.55).toInt()
            if (seekBar.id == R.id.RseekBar.toInt()) {
                red = value
            }
            if (seekBar.id == R.id.GseekBar.toInt()) {
                green = value
            }
            if (seekBar.id == R.id.BseekBar.toInt()) {
                blue = value
            }
            updateColors()
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    private fun updateColors() {
        val mainView: View = findViewById(R.id.main)
        val color = Color.rgb(red, green, blue)
        mainView.setBackgroundColor(color)

        val textView: TextView = findViewById(R.id.textView)
        textView.text = "red: " + red + "\nblue: " + blue + "\ngreen: " + green
        val reverseColor = Color.rgb(255 - red, 255 - green, 255 - blue)
        textView.setTextColor(reverseColor)
    }
}
