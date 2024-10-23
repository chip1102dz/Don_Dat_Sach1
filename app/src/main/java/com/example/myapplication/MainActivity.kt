package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SachAdapter
    lateinit var db: SachDataBase
    var list = mutableListOf<Sach>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.rcv
        adapter = SachAdapter(this)
        db = SachDataBase(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recyclerView.adapter = adapter

        binding.btnLoad.setOnClickListener {
            getAllData()
        }
        binding.btnAdd.setOnClickListener {
            insertData()
        }
        getAllData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun insertData() {
        val name = binding.name.text.toString()
        val theloai = when{
            binding.rdbTrinhtham.isChecked -> "Trinh thám"
            binding.rdbTieuthuyet.isChecked -> "Tiểu thuyết"
            binding.rdbGiaokhoa.isChecked -> "Giáo khoa"
            else -> {""}
        }
        var image = when{
            binding.rdbTrinhtham.isChecked -> R.drawable.ic_launcher_background
            binding.rdbTieuthuyet.isChecked -> R.drawable.ic_launcher_foreground
            binding.rdbGiaokhoa.isChecked -> R.drawable.ic_launcher_background
            else -> {0}
        }
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(theloai)){
            Toast.makeText(this, "HÃY NHẬP ĐỦ THÔNG TIN", Toast.LENGTH_SHORT).show()
            return
        }
        val sach = Sach(name = name, theloai = theloai, image = image)
        db.insertSach(sach)
        binding.name.setText("")
        binding.rdbTrinhtham.isChecked = false
        binding.rdbTieuthuyet.isChecked = false
        binding.rdbGiaokhoa.isChecked = false

    }

    private fun getAllData() {
        list = db.getAllSach()
        adapter.setData(list)
    }
}