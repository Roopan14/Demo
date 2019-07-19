package com.example.demo

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import java.util.*
import android.content.Intent
import androidx.core.view.isVisible
import com.example.demo.callbacks.FragmentCallbacks
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.intsig.csopen.sdk.CSOpenAPI
import com.intsig.csopen.sdk.CSOpenAPIParam
import com.intsig.csopen.sdk.CSOpenApiFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), FacebookCallback<LoginResult>, FragmentCallbacks {

    private var isLoggedIn: Boolean = false
    private val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLogo()

        login_button.setPermissions(arrayListOf("email", "public_profile"))
        login_button.registerCallback(callbackManager, this)
    }

    private fun getHash() {
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

    private fun checkLogin() {

        val accessToken = AccessToken.getCurrentAccessToken()
        isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn) {
            val csopneAPI = CSOpenApiFactory.createCSOpenApi(this, "KLPt0gTtYUyP0fTXV8aH44e7", "userid")
            csopneAPI.scanImage(this, 0, CSOpenAPIParam("source", "", "", "", 0.1f))
        }
        else {

        }

    }

    private fun showLogo() {
        supportFragmentManager.beginTransaction().replace(android.R.id.content, CSLogo()).addToBackStack("logo").commit()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSuccess(result: LoginResult?) {
        Log.d("DEMO", "FB login success")
    }

    override fun onCancel() {
        // handle cancel case
    }

    override fun onError(error: FacebookException?) {
        // handle error case
    }

    override fun onLogoRemoved() {
        checkLogin()
    }
}
