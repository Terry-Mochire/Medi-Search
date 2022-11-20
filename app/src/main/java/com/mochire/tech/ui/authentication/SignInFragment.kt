package com.mochire.tech.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.mochire.tech.MainActivity
import com.mochire.tech.R
import com.mochire.tech.ui.authentication.SignInFragment.Companion.newInstance

class SignInFragment: Fragment(R.layout.sign_in) {

    private lateinit var auth: FirebaseAuth

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val signInButton: MaterialButton = view?.findViewById(R.id.signInButton)!!
        signInButton.setOnClickListener{
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.d("SignInFragment", "User is already signed in")
            } else {
                Log.d("SignInFragment", "User is not signed in")
            }

            val email = view?.findViewById<EditText>(R.id.userEmailSignIn)?.text.toString()
            val password = view?.findViewById<EditText>(R.id.userPasswordSignIn)?.text.toString()

            if (validateForm()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("SignInFragment", "signInWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("SignInFragment", "signInWithEmail:failure", task.exception)
                            Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateForm(): Boolean {
        var valid = true
        val email = view?.findViewById<EditText>(R.id.userEmailSignIn)?.text.toString()
        val password = view?.findViewById<EditText>(R.id.userPasswordSignIn)?.text.toString()

        if (email.isEmpty()) {
            view?.findViewById<EditText>(R.id.userEmailSignIn)?.error = "Required"
            valid = false
        } else if (password.isEmpty()) {
            view?.findViewById<EditText>(R.id.userPasswordSignIn)?.error = "Required."
            valid = false
        }

        return valid
    }

}