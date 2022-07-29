package com.example.notesandpasswordapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val UserId= intent.getStringExtra("user_id")
        val useremail = intent.getStringExtra("email")


        //navbar code starts here..

        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Passing the ActionBarToggle action into the drawerListener
        actionBarToggle=  ActionBarDrawerToggle(this, drawerLayout, R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button
        //when the drawer layout is open (for left and right swipe)
        actionBarToggle.syncState()

        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)

        navView.setNavigationItemSelectedListener { menu->
            when(menu.itemId){
                R.id.userProfile ->{
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.starred ->{
                    Toast.makeText(this, "My starred", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings ->{
                    Toast.makeText(this, "My Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.logout ->{
                    FirebaseAuth.getInstance().signOut()

                    startActivity(Intent(this, LogInActivity::class.java))
                    finish()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(nav_text)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}