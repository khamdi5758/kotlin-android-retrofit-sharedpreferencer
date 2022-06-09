package com.lazday.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.lazday.sharedpreferences.helper.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.lazday.sharedpreferences.api.*
import com.lazday.sharedpreferences.response.*

class MainActivity : AppCompatActivity() {
    private  val TAG:String = "MainActivity"
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefHelper = PrefHelper(this)

        buttonLogin.setOnClickListener {
//            if (editUsername.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
//
//
//
//                saveSession( editUsername.text.toString(), editPassword.text.toString() )
//                showMessage( "Berhasil login" )
//                moveIntent()
//            }
            login()

        }
    }

    fun login(){

        if (editUsername.text.isEmpty()) {
            editUsername.error = "Nama Akun tidak boleh kosong"
            editUsername.requestFocus()
            return
        }else if (editPassword.text.isEmpty()) {
            editPassword.error = "Password tidak boleh kosong"
            editPassword.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.login(editUsername.text.toString(), editPassword.text.toString())
            .enqueue(object :Callback<ResponUser> {
                override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {

                val respon = response.body()!!
                if(respon.success){
                    printlog(respon.toString())
                    showuser(respon.user)
//                    val datauser = respon.user
//                    for (user in datauser) {
//                      saveSession(respon.user?., user.password, user.idUser, user.nama, user.noTelp)
//                    }
                    moveIntent()
                    Toast.makeText(this@MainActivity, "Masuk Akun Berhasil -" + respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@MainActivity, "Nama Akun dan password Salah  -" + respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
            }
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    val respon = response.body()
//                    printlog(respon.toString())
//                }
//
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
//                }

            })
    }

    override fun onStart() {
        super.onStart()
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            moveIntent()
        }
    }

    private fun saveSession(username: String, password: String, iduser:String, nama: String, notelp:String){
        prefHelper.put( Constant.PREF_USERNAME, username )
        prefHelper.put( Constant.PREF_PASSWORD, password )
        prefHelper.put( Constant.pref_iduser, iduser)
        prefHelper.put( Constant.pref_nama, nama)
        prefHelper.put( Constant.pref_notelp, notelp)
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    private fun moveIntent(){
//        startActivity(Intent(this, ::class.java))
//        finish()
        val intent = Intent(this, SecondActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun printlog(message: String){
        Log.d(TAG,message)
    }

    private fun showuser(users: List<User>){
        for (user in users){
            printlog("username : ${user.username}\n " +
                    "iduser : ${user.idUser}\n" +
                    "nama :${user.nama}\n" +
                    "notelp: ${user.noTelp}")
            saveSession("${user.username}",
                        "${user.password}",
                        "${user.idUser}",
                        "${user.nama}",
                        "${user.noTelp}")

        }
    }
}