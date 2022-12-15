package com.mochire.tech.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.mochire.tech.database.UserApplication
import com.mochire.tech.database.entity.User
import com.mochire.tech.databinding.FragmentProfileBinding
import com.mochire.tech.viewmodels.ProfileViewModel
import com.mochire.tech.viewmodels.ProfileViewModelFactory


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var user = FirebaseAuth.getInstance().currentUser
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel by activityViewModels<ProfileViewModel> {
            ProfileViewModelFactory((activity?.application as UserApplication).database.userDao())
        }
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val saveButton:Button = binding.saveButton
        val name: EditText = binding.profileFormUserName
        val phoneNumber: EditText = binding.profileFormPhoneNumber
        val age: EditText = binding.profileFormAge
        val knownConditions: EditText = binding.profileFormKnownConditions
        var gender: String = " "
        if(binding.switchFemale.isChecked) {
            binding.switchMale.isChecked = false
            gender = "female"
        } else{
            binding.switchMale.isChecked = true
            gender = "male"
        }



        saveButton.setOnClickListener {
            val userName = name.text.toString()
            val age = age.text.toString().toInt()
            val userPhoneNumber = phoneNumber.text.toString().toInt()
            val userConditions = knownConditions.text.toString()

            val user = User(0, name = userName, gender = gender, age = age, phoneNumber = userPhoneNumber, conditions = userConditions)
            profileViewModel.createUser(user)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}