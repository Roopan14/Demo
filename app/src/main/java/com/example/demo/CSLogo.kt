package com.example.demo

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable

/**
 * Created by Roopan C on 18/7/19.
 */
class CSLogo: Fragment() {

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cslogo, container, false)
    }

    override fun onStart() {
        super.onStart()

        // removing logo fragment after 1.5 secs
        Handler().postDelayed( {
            fragmentManager?.popBackStack()
        }, 1500)
    }

}