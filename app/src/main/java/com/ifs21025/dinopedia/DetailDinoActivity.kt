package com.ifs21025.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21025.dinopedia.databinding.ActivityDetailDinoBinding

class DetailDinoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDinoBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dino ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDinoDetailPicture.setImageResource(dino.pict)
        binding.tvDinoDetailName.text = dino.name
        binding.tvDinoDetailDesc.text = dino.description
        binding.tvDinoDetailCharacteristic.text = dino.characteristic
        binding.tvDinoDetailKelompok.text = dino.kelompok
        binding.tvDinoDetailHabitat.text = dino.habitat
        binding.tvDinoDetailFood.text = dino.makanan
        binding.tvDinoDetailLength.text = dino.panjang
        binding.tvDinoDetailHeight.text = dino.tinggi
        binding.tvDinoDetailWeight.text = dino.bobot
        binding.tvDinoDetailWeak.text = dino.kelemahan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}