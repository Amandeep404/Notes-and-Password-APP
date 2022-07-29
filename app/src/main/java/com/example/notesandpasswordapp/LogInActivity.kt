package com.example.notesandpasswordapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.log_in_page.*
import kotlinx.android.synthetic.main.reset_password.*
import kotlinx.android.synthetic.main.sign_up_page.*

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_page)

        auth = FirebaseAuth.getInstance()

        LogInButton.setOnClickListener {
            login()
        }
        signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        forgotPasswordTv.setOnClickListener {
            Toast.makeText(this, "Feature coming soon", Toast.LENGTH_SHORT).show()
        }
        forgotPasswordTv.setOnClickListener{
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        var user = Firebase.auth.currentUser
        if (user != null) {
         goToRegister()
        } else {
            val email = emailTextField.text.toString()
            val password = passwordTextField.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user_id", firebaseUser.uid)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this, "Invalid User-Id or password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun goToRegister(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToResetPassword(){
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
    }


}
