package com.example.versonone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product.*
class Product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.title = "作品介紹"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val jsonFileRes = JsonFileRes().readJsonFile(this)
        val productImageIndex = intent.getIntExtra("index", 0)
        val actImageIndex = intent.getIntExtra("image", 0)
        Glide.with(this).load(jsonFileRes.act[actImageIndex].product[productImageIndex].image).into(ivProduct)
        tvProductDescribe.text = jsonFileRes.act[actImageIndex].product[productImageIndex].describe
    }
}
