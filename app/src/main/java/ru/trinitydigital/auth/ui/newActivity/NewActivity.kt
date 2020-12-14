package ru.trinitydigital.auth.ui.newActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.trinitydigital.auth.R
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.remote.RetrofitBuilder
import ru.trinitydigital.auth.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewBinding
    private val viewModel by viewModel<NewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.getProfileModel().observe(this, {
            binding.tvUserName.text = it?.firstName
        })
    }
}