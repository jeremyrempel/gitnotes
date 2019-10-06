package com.github.jeremyrempel.gitnotes.android

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.github.jeremyrempel.gitnotes.Config
import com.github.jeremyrempel.gitnotes.android.di.DaggerAppComponent
import com.github.jeremyrempel.gitnotes.android.di.ServiceModule
import com.github.jeremyrempel.gitnotes.android.ui.AboutFragment
import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.android.ui.SettingsFragment
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.navigation.NavScreen.List
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationCallback,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        // init dagger graph
        val dagger = DaggerAppComponent
            .builder()
            .serviceModule(ServiceModule(applicationContext))
            .build()

        supportFragmentManager.fragmentFactory = dagger.fragFactory()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        setupNavDrawer()

        if (savedInstanceState == null) {
            navigateTo(List("/", false))
        }
    }

    private fun setupNavDrawer() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun showBackButton(enable: Boolean) {
        if (enable) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            toggle.isDrawerIndicatorEnabled = false
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toggle.toolbarNavigationClickListener = View.OnClickListener {
                onBackPressed()
                if (supportFragmentManager.backStackEntryCount <= 1) {
                    showBackButton(false)
                }
            }
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            toggle.isDrawerIndicatorEnabled = true
            toggle.toolbarNavigationClickListener = null
        }
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
            R.id.nav_settings -> navigateTo(NavScreen.Settings())
            R.id.nav_about -> navigateTo(NavScreen.About())
            R.id.nav_github -> {
                val url = Config.GITHUB_URL
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            R.id.nav_mynotes -> navigateTo(List(null, false))
        }
        return true
    }

    override fun navigateTo(screen: NavScreen) {
        val frag = when (screen) {
            is List -> {
                title = getString(R.string.title_home)
                val frag = buildContentsListFragment()

                Bundle().apply {
                    putString(ContentsListFragment.ARG_PATH, screen.path)
                    frag.arguments = this
                }

                frag
            }
            is NavScreen.About -> {
                title = getString(R.string.title_about)
                buildAboutFragment()
            }
            is NavScreen.Settings -> {
                title = getString(R.string.title_settings)
                buildSettingsFragment()
            }
        }

        showBackButton(screen.showBackButton)
        drawer.closeDrawers()

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

    private fun buildAboutFragment(): Fragment {
        return supportFragmentManager
            .fragmentFactory
            .instantiate(
                classLoader,
                AboutFragment::class.java.canonicalName!!
            )
    }

    private fun buildSettingsFragment(): Fragment {
        return supportFragmentManager
            .fragmentFactory
            .instantiate(
                classLoader,
                SettingsFragment::class.java.canonicalName!!
            )
    }
}
