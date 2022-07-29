package com.example.notesandpasswordapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.sign_up_page.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_page)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

       //signUp btn click
        signUpBtn.setOnClickListener{

            register()
        }
        loginTv.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }

    }
private fun register(){
    val email = inputUserName.text.toString()
    val password = userInutPassword.text.toString()

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
        if(task.isSuccessful){
            val firebaseUser :FirebaseUser=task.result!!.user!!
            val intent =Intent(this, MainActivity::class.java)
            intent.putExtra("user_id", firebaseUser.uid)
            intent.putExtra("email", email)
            startActivity(intent)
            finish()
        }
    }.addOnFailureListener { exception ->
        Toast.makeText(this,"Invalid details, Try again" ,Toast.LENGTH_LONG).show()
    }
}
    fun goToLogin(view: View){
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser!= null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}




