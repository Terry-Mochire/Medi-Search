package com.mochire.tech.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.mochire.tech.MainActivity
import com.mochire.tech.R
import com.mochire.tech.ui.authentication.SignInFragment.Companion.newInstance

class SignInFragment: Fragment(R.layout.sign_in) {

    companion object {
        fun newInstance(): SignInFragment = SignInFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signUpInstead: TextView = view.findViewById(R.id.signUpInstead)
        signUpInstead.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.signInFragmentContainer, SignUpFragment.newInstance())
                ?.commitNow()
        }
    }

}