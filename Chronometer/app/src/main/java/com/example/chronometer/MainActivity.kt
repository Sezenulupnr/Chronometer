package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.chronometer.common.viewBinding
import com.example.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding (ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var stopTime:Long = 0

        with(binding){
            btnStart.setOnClickListener{
                chronomenter.base = SystemClock.elapsedRealtime() + stopTime
                //Kronometre içine sistem saatini aktardık.
                chronomenter.start()
                //Kronometreyi çalıştır.

                btnStart.visibility = View.GONE
                btnPause.visibility = View.VISIBLE
                //Pause ve Start butonlarının görünürlüğünü değiştirdik.

                imageViewStart.setImageDrawable(getDrawable(R.drawable.pause))
                //İmageViewStarta tıklanınca yerine drawable dosyasından pause image atanacak.
            }

            btnPause.setOnClickListener{
                stopTime = chronomenter.base - SystemClock.elapsedRealtime()
                chronomenter.stop()
                btnPause.visibility = View.GONE
                btnStart.visibility = View.VISIBLE
                imageViewStart.setImageDrawable(getDrawable(R.drawable.start))
            }

            btnRestart.setOnClickListener{
                chronomenter.base = SystemClock.elapsedRealtime()
                chronomenter.stop()
                stopTime = 0
                btnPause.visibility = View.GONE
                btnStart.visibility = View.VISIBLE
                imageViewStart.setImageDrawable(getDrawable(R.drawable.start))
            }
        }
    }
}