package com.androidstudio.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    private lateinit var dbDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var nvDrawer: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Toolbar setup
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inisialisasi view DrawerLayout dan NavigationView
        dbDrawer = findViewById(R.id.dashboardDrawerLayout)
        nvDrawer = findViewById(R.id.navigationView)

        // Setup konten drawer
        setupDrawerContent(nvDrawer)

        // Set fragment pertama kali aplikasi dibuka
        if (savedInstanceState == null) {
            selectDrawerItem(nvDrawer.menu.findItem(R.id.menuDashboard))
        }

        // Memberikan fungsi untuk membuka DrawerLayout saat tombol di toolbar diklik
        toolbar.setNavigationOnClickListener {
            dbDrawer.openDrawer(GravityCompat.START)
            reorderViews()
        }
    }

    private fun reorderViews() {
        val parentLayout = findViewById<ViewGroup>(R.id.dashboardDrawerLayout)

        // Remove and add NavigationView to change the Z-order
        parentLayout.removeView(nvDrawer)
        parentLayout.addView(nvDrawer)
    }

    private fun setupDrawerContent(nvDrawer: NavigationView?) {
        nvDrawer?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuMasterData -> {
                    // Menangani klik pada menuMasterData
                    handleMasterDataClick(menuItem)
                }
                else -> {
                    // Memanggil fungsi untuk menangani item menu yang dipilih
                    selectDrawerItem(menuItem)
                }
            }
            true
        }
    }

    private fun selectDrawerItem(item: MenuItem) {
        var fragment: Fragment? = null
        var fragmentClass: Class<out Fragment> = DashboardFragment::class.java

        // Menentukan tindakan berdasarkan item menu yang dipilih
        when (item.itemId) {
            R.id.menuDashboard -> {
                // Tidak melakukan pergantian activity karena sudah berada di Dashboard
                item.isChecked = true
                setTitle(item.title)
                dbDrawer.closeDrawers()
            }
            R.id.menuMasterData -> {
                // Menangani klik pada menuMasterData
                item.isChecked = true
                handleMasterDataClick(item)
            }
            R.id.menuPemakaianBarang -> fragmentClass = PemakaianFragment::class.java
            R.id.menuPengembalianBarang -> fragmentClass = PengembalianFragment::class.java
            R.id.menuLaporan -> fragmentClass = LaporanFragment::class.java
            R.id.menuDosen -> {
                // Menangani klik pada submenu Dosen
                handleSubmenuClick(item)
            }
        }

        // Membuat instance fragment dan menggantikan tampilan fragment saat ini
        try {
            fragment = fragmentClass.newInstance()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).commit()

        // Menandai item menu sebagai terpilih dan mengatur judul
        item.isChecked = true
        setTitle(item.title)

        // Menutup drawer setelah memilih menu
        dbDrawer.closeDrawers()
    }

    private fun handleMasterDataClick(item: MenuItem) {
        // Menangani klik pada menuMasterData dengan menampilkan submenu
        when (item.itemId) {
            R.id.menuDosen -> {
                // Mengarahkan ke LecturerDataFragment
                selectDrawerItem(nvDrawer.menu.findItem(R.id.menuDosen))
            }
            R.id.menuMahasiswa -> {
                // Mengarahkan ke StudentDataFragment
                // Tambahkan sesuai kebutuhan
            }
            R.id.menuInventory -> {
                // Mengarahkan ke InventoryFragment
                // Tambahkan sesuai kebutuhan
            }
        }

        // Menutup drawer setelah memilih submenu
        dbDrawer.closeDrawers()
    }

    private fun handleSubmenuClick(item: MenuItem) {
        // Menangani klik pada submenu, dalam hal ini, menu Dosen
        val fragmentClass = MasterDataFragment::class.java
        var fragment: Fragment? = null

        // Membuat instance fragment dan menggantikan tampilan fragment saat ini
        try {
            fragment = fragmentClass.newInstance()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).commit()

        // Menandai item submenu sebagai terpilih dan mengatur judul
        item.isChecked = true
        setTitle(item.title)

        // Menutup drawer setelah memilih submenu
        dbDrawer.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Menangani klik tombol home pada toolbar untuk membuka drawer
        when (item.itemId) {
            android.R.id.home -> {
                dbDrawer.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
