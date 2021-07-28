package com.ncr.satemobile

 import android.os.Bundle
 import android.view.View
 import android.view.WindowManager
 import androidx.appcompat.app.AppCompatActivity
 import androidx.drawerlayout.widget.DrawerLayout
 import androidx.navigation.findNavController
 import androidx.navigation.ui.AppBarConfiguration
 import androidx.navigation.ui.navigateUp
 import androidx.navigation.ui.setupActionBarWithNavController
 import androidx.navigation.ui.setupWithNavController
 import com.ncr.satemobile.databinding.ActivityMainBinding
 import com.google.android.material.navigation.NavigationView
 import com.pixplicity.easyprefs.library.Prefs
 import kotlinx.android.synthetic.main.nav_header_main.*
 import kotlinx.android.synthetic.main.nav_header_main.view.*


//nav_host_fragment_content_main
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding?.appBarMain.toolbar)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.FirstFragment, R.id.scenario, R.id.actionsFragment, R.id.testsetFragment,R.id.dashboardFragment
            ), drawerLayout
        )
        val header: View = navView.getHeaderView(0)


        val username = Prefs.getString("userName", null)
        val email = Prefs.getString("userEmail", null)
         header.header_username.text = username
        header.header_useremail.text = email

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

     }

  /*  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}