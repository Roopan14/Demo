package com.example.demo

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLogo()

        checkLogin()
    }

    private fun checkLogin() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.demo",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("TAG_KEY_HASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

    private fun showLogo() {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, CSLogo()).addToBackStack("logo").commit()
    }
}
