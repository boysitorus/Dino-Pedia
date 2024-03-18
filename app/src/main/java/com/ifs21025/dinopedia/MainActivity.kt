package com.ifs21025.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21025.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamily = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.about_btn -> {
                val intent = Intent(this@MainActivity ,AboutActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListFamily(): ArrayList<Family> {
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

        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val family = Family(dataName[i],
                dataPict.getResourceId(i, -1), dataDescription[i], dataPeriode[i],
                dataCharacteristic[i], dataHabitat[i], dataPerilaku[i], dataKlasifikasi[i])
            listFamily.add(family)
        }
        return listFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamily.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamily.layoutManager =
                LinearLayoutManager(this)
        }

        val listFamilyAdapter = ListFamilyAdapter(dataFamily)
        binding.rvFamily.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFamily(data)
            }
        })
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(this@MainActivity,
            DetailFamilyActivity::class.java)
        intentWithData.putExtra(DetailFamilyActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}
