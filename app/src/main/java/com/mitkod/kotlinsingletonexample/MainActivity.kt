package com.mitkod.kotlinsingletonexample

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.mitkod.kotlinsingletonexample.databinding.ActivityMainBinding
import com.mitkod.kotlinsingletonexample.model.User

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        viewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }

    private fun setupObservers() {
        viewModel.user.observe(this) {
            loadUserData(it)
            binding.pbDetails.visibility = View.GONE
            binding.llUserDetails.visibility = View.VISIBLE
        }
    }

    private fun loadUserData(user: User) {
        with(binding) {
            // Set toolbar title to user's name
            setSupportActionBar(toolbar)
            supportActionBar?.title = "${user.firstName} ${user.maidenName} ${user.lastName}"

            // Load profile image using Coil
            profileImage.load(user.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            age.text = user.age?.toString()
            gender.text = user.gender
            email.text = user.email
            phone.text = user.phone
            username.text = user.username
            password.text = user.password
            birthDate.text = user.birthDate
            bloodGroup.text = user.bloodGroup
            height.text = user.height?.toString()
            weight.text = user.weight?.toString()
            eyeColor.text = user.eyeColor
            hairColor.text = user.hair?.color
            hairType.text = user.hair?.type
            ip.text = user.ip
            address.text = user.address?.address
            city.text = user.address?.city
            state.text = user.address?.state
            stateCode.text = user.address?.stateCode
            postalCode.text = user.address?.postalCode
            country.text = user.address?.country
            lat.text = user.address?.coordinates?.lat?.toString()
            lng.text = user.address?.coordinates?.lng?.toString()
            macAddress.text = user.macAddress
            university.text = user.university
            cardExpire.text = user.bank?.cardExpire
            cardNumber.text = user.bank?.cardNumber
            cardType.text = user.bank?.cardType
            currency.text = user.bank?.currency
            iban.text = user.bank?.iban
            companyDepartment.text = user.company?.department
            companyName.text = user.company?.name
            companyTitle.text = user.company?.title
            companyAddress.text = user.company?.address?.address
            ein.text = user.ein
            ssn.text = user.ssn
            cryptoCoin.text = user.crypto?.coin
            cryptoWallet.text = user.crypto?.wallet
            cryptoNetwork.text = user.crypto?.network
            role.text = user.role
        }
    }
}