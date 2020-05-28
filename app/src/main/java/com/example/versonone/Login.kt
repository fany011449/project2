package com.example.versonone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
class Login : AppCompatActivity() {
    val testForDatabase = "a"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        cbKeepLogin.setOnCheckedChangeListener {  buttonView, isChecked ->
//            val account = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
//                .getString("userA","")
//            val passwd = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
//                .getString("userP","")
//            etAccout.setText(account)
//            etPass.setText(passwd)
//        }
    }

    fun login () {
        val userAccount = etAccout.text.toString()
        val userPassWord = etAccout.text.toString()
            if (userAccount == "aa" && userPassWord == "aa"){
//                getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
//                    .edit()
//                    .putString("userA",userAccount)
//                    .putString("userP",userPassWord)
//                    .apply()
//                intent = Intent(this,PersonalInfo::class.java)
//                startActivity(intent)
            } else {
                Toast.makeText(this,"帳號密碼錯誤",Toast.LENGTH_SHORT).show()
        }
    }
    fun register () {

    }
}
