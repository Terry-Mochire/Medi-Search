package com.mochire.tech.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.mochire.tech.MainActivity
import com.mochire.tech.R
import kotlin.math.log

class SignUpFragment: Fragment(R.layout.sign_up) {

    private lateinit var auth: FirebaseAuth

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val signUpButton: Button = view?.findViewById(R.id.signUpButton)!!
        signUpButton.setOnClickListener{
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.d("SignUpFragment", "User is already signed in")
            } else {
                Log.d("SignUpFragment", "User is not signed in")
            }


            val email = view?.findViewById<EditText>(R.id.userEmail)?.text.toString()
            val password = view?.findViewById<EditText>(R.id.userPassword)?.text.toString()

            auth.createUserWithEmailAndPassword(email, password).
            addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SignUpFragment", "User created successfully")
                    Toast.makeText(context, "User created successfully", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("SignUpFragment", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }




}