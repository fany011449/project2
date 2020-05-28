package com.example.versonone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_act1.*
class  Actor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.title = "演員簡介"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1)
        val jsonFileRes = JsonFileRes().readJsonFile(this)
        val passFormMainActivityIndex = intent.getIntExtra("index", 0)
        if (jsonFileRes.act[passFormMainActivityIndex].actimage != null) {
            Glide.with(this).load(jsonFileRes.act[passFormMainActivityIndex].actimage).into(ivActorPhoto)
        } else {
            Glide.with(this).load(jsonFileRes.act[passFormMainActivityIndex].actimageurl).into(ivActorPhoto)
        }
        tvActorDescribe.text = jsonFileRes.act[passFormMainActivityIndex].actdescribe
        rvPdoductCollect.adapter = RecyclerViewAdapter(this, passFormMainActivityIndex,"product",jsonFileRes)
        rvPdoductCollect.layoutManager = GridLayoutManager(this, 3)
    }
}
