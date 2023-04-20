package com.letung.signinfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.letung.signinfacebook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogout.setOnClickListener {
            if(AccessToken.getCurrentAccessToken()!=null){
                AccessToken.setCurrentAccessToken(null)
            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val accessToken = AccessToken.getCurrentAccessToken()
        val request = GraphRequest.newMeRequest(accessToken){ `object`, response ->
            binding.fbID.text = `object`?.getString("id")
//            binding.fbEmail.text = `object`?.getString("email")
            binding.fbName.text = `object`?.getString("name")
//            val url = `object`?.getJSONObject("picture")?.getJSONObject("data")?.getString("url")
//            Glide
//                .with(applicationContext)
//                .load(url)
//                .into(binding.avatar)
        }

        val params = Bundle()
        params.putString("field", "id,name,email,picture,type(large)")
        request.parameters = params
        request.executeAsync()
    }
}