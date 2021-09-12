package com.example.freskita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.freskita.ui.compras.ComprasActivity

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
                // 1. creo el Intent
                val go2Compras = Intent(this, ComprasActivity::class.java)
                // 2. Inicializar la actividad con el Intent creado
                this.startActivity(go2Compras)
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