package com.ncr.satemobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.ncr.satemobile.veiwmodel.UserViewModel
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {

            val model = UserViewModel()
            model.getLoginDetails(username.text.toString()).observe(this, {

                Prefs.putString("userProject", it.currentProject)
                Prefs.putString("userName", it.name)
                Prefs.putString("userEmail", it.email)
                val b=Bundle()
                b.putSerializable("user",it)
                val bundle = bundleOf("username" to it.name,"email" to it.email)
                val intent = Intent(this, MainActivity::class.java)
               //
                // intent.putExtras(b)
                startActivity(intent)
                finish()

            })


        }
    }

}


