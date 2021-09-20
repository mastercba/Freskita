package com.example.freskita.ui.produccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.freskita.R
import com.example.freskita.model.ComprasModel
import com.example.freskita.ui.compras.NuevaCompraActivity

class ProduccionActivity : AppCompatActivity() {

    val arrayList = ArrayList<ComprasModel>()
    val displayList = ArrayList<ComprasModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produccion)

        getSiembras()

    }

    private fun getSiembras(){
    }

    //------------------------------------------------------------------->
    //<!-----AppBar Menu, Botones:siembra ------------------------------->
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_compras,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ingresar -> {
//                val intent34 = Intent(this, NuevaSiembraActivity::class.java)
//                this.startActivity(intent34)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}