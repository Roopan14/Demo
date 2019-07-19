package com.example.demo

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import com.example.demo.callbacks.FragmentCallbacks

/**
 * Created by Roopan C on 18/7/19.
 */
class CSLogo: Fragment() {

    private lateinit var fragmentCallback: FragmentCallbacks

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cslogo, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        fragmentCallback = activity as FragmentCallbacks
    }

    override fun onStart() {
        super.onStart()

        // removing logo fragment after 1.5 secs
        Handler().postDelayed( {
            fragmentManager?.popBackStack()
            fragmentCallback.onLogoRemoved()
        }, 1500)
    }

}