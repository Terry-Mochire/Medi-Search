package com.mochire.tech.ui.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mochire.tech.R

class SignUpFragment: Fragment(R.layout.sign_up) {

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInInstead: TextView = view.findViewById(R.id.signInInstead)
        signInInstead.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.signInFragmentContainer, SignInFragment.newInstance())
                ?.commitNow()
        }
    }
}