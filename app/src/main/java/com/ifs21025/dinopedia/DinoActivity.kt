package com.ifs21025.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21025.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinoBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDino.setHasFixedSize(false)
        dataDino.addAll(getListDino())
        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
               EXTRA_FAMILY,
                Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }

        val dataName =
            resources.getStringArray(R.array.dino_name)
        val dataPict =
            resources.obtainTypedArray(R.array.dino_pict)
        val dataDescription =
            resources.getStringArray(R.array.dino_description)
        val dataCharacteristic =
            resources.getStringArray(R.array.dino_characteristic)
        val dataKelompok =
            resources.getStringArray(R.array.dino_kelompok)
        val dataHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dataFood =
            resources.getStringArray(R.array.dino_food)
        val dataLength =
            resources.getStringArray(R.array.dino_length)
        val dataHeight =
            resources.getStringArray(R.array.dino_height)
        val dataWeight =
            resources.getStringArray(R.array.dino_weight)
        val dataWeak =
            resources.getStringArray(R.array.dino_weak)

        val startIndex = family?.startIndex
        val endIndex = family?.endIndex

        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            if(i == startIndex || i == endIndex){
                val dino = Dino(dataName[i],
                    dataPict.getResourceId(i, -1), dataDescription[i], dataCharacteristic[i],
                    dataKelompok[i], dataHabitat[i], dataFood[i], dataLength[i], dataHeight[i], dataWeight[i], dataWeak[i])
                listDino.add(dino)
            }
        }

        return listDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoActivity,
            DetailDinoActivity::class.java)
        intentWithData.putExtra(DetailDinoActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object{
        const val EXTRA_FAMILY = "extra_family"
    }
}
