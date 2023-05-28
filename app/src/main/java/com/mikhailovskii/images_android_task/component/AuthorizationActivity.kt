package com.mikhailovskii.images_android_task.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mikhailovskii.images_android_task.R

class AuthorizationActivity : AppCompatActivity() {

    private var navController: NavController? = null

    private val appBarConfiguration by lazy { AppBarConfiguration(topLevelDestinationIds = setOf(R.id.loginFragment)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fcv_auth,
        ) as NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(findViewById(R.id.toolbar))

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() = navController?.navigateUp(appBarConfiguration) ?: false
}
