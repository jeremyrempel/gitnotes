package com.github.jeremyrempel.gitnotes.android

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.github.jeremyrempel.gitnotes.android.di.AppComponent
import com.github.jeremyrempel.gitnotes.android.di.DaggerAppComponent
import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.navigation.NavScreen.List
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationCallback,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        // init dagger graph
        val dagger: AppComponent = DaggerAppComponent.create()

        supportFragmentManager.fragmentFactory = dagger.fragFactory()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, buildContentsListFragment())
                .commitNow()
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            R.id.nav_about -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
            R.id.nav_github -> Toast.makeText(
                this,
                "Github",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    override fun navigateTo(screen: NavScreen) {
        val frag = when (screen) {
            is List -> {
                val frag = buildContentsListFragment()

                Bundle().apply {
                    putString(ContentsListFragment.ARG_PATH, screen.path)
                    frag.arguments = this
                }

                frag
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, frag)
            .addToBackStack(null)
            .commit()
    }

    private fun buildContentsListFragment(): Fragment {
        return supportFragmentManager
            .fragmentFactory
            .instantiate(classLoader, ContentsListFragment::class.java.canonicalName!!)
    }
}

interface NavigationCallback {
    fun navigateTo(screen: NavScreen)
}
