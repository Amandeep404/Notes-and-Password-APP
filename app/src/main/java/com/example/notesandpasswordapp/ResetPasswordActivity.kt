package com.example.notesandpasswordapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.reset_password.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password)

        auth = FirebaseAuth.getInstance()

        resetPasswordBtn.setOnClickListener{
            resetPassword()
            Executors.newSingleThreadScheduledExecutor().schedule({
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()

            }, 2, TimeUnit.SECONDS)
        }
    }
    private fun resetPassword(){
        val email= resetPasswordEmail.text.toString()
        auth.sendPasswordResetEmail(email).addOnCompleteListener{task ->
            if(task.isSuccessful){
                Toast.makeText(this, "verification mail sent to your E-mail Id", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener{exception ->
            Toast.makeText(this, "Invalid mail", Toast.LENGTH_SHORT).show()
        }
    }
}