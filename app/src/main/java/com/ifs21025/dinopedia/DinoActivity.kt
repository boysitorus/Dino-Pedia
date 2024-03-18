package com.ifs21025.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
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
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val dataName =
            resources.getStringArray(R.array.family_name)
        val dataPict =
            resources.obtainTypedArray(R.array.family_pict)
        val dataDescription =
            resources.getStringArray(R.array.family_description)
        val dataPeriode =
            resources.getStringArray(R.array.family_periode)
        val dataCharacteristic =
            resources.getStringArray(R.array.family_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.family_habitat)
        val dataPerilaku =
            resources.getStringArray(R.array.family_perilaku)
        val dataKlasifikasi =
            resources.getStringArray(R.array.family_klasifikasi)

        val listDino = ArrayList<Dino>()
//        for (i in dataName.indices) {
//            val family = Dino(dataName[i],
//                dataPict.getResourceId(i, -1), dataDescription[i], dataPeriode[i],
//                dataCharacteristic[i], dataHabitat[i], dataPerilaku[i], dataKlasifikasi[i])
//            listDino.add(family)
//        }
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

    private fun showSelectedDino(family: Dino) {
        val intentWithData = Intent(this@DinoActivity,
            DetailDinoActivity::class.java)
//        intentWithData.putExtra(DetailDinoActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}
