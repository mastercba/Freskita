package com.example.freskita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




    //<!-----AppBar Menu, Botones:produccion,compras,ventas & salir------>
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.produccion -> {
                // 1. creo el Intent
                //val go2Siembra = Intent(this, SiembraActivity::class.java)
                // 2. Inicializar la actividad con el Intent creado
                //this.startActivity(go2Siembra)
            }
            R.id.compras -> {
                //getSiembra()
            }
            R.id.ventas -> {
                //getSiembra()
            }
            R.id.salir -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}