package com.example.freskita.ui.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.example.freskita.R

class DetalleCompraActivity : AppCompatActivity() {

    private lateinit var txtEDdateCompra: TextView
    private lateinit var txtEDdetalleCompra: TextView
    private lateinit var txtEDfacturaCompra: TextView
    private lateinit var txtEDmontoCompra: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_compra)

        txtEDdateCompra = findViewById(R.id.fechaRxTV)
        txtEDdetalleCompra = findViewById(R.id.detalleRxET)
        txtEDfacturaCompra = findViewById(R.id.facturaRxET)
        txtEDmontoCompra = findViewById(R.id.montoRxET)

        var eId = intent.getStringExtra("iId")
        var eDate = intent.getStringExtra("iDate")
        var eDetalle = intent.getStringExtra("iDetalle")
        var eFactura = intent.getStringExtra("iFactura")
        var eMonto = intent.getStringExtra("iMonto")

        //txtEDdateCompra.text = eId.toString()
        txtEDdateCompra.text = eDate.toString()
        txtEDdetalleCompra.text = eDetalle
        txtEDfacturaCompra.text = eFactura
        txtEDmontoCompra.text = eMonto

    }



    //<!---------------AppBar Menu, Botones:edit,delete------------------>
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_delete,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit -> {
//                //crear intent con kotlin
//                val intent34 = Intent(this,NuevaCompraActivity::class.java)
//                //iniciar el activity
//                this.startActivity(intent34)
            }
            R.id.delete -> {
//                //crear intent con kotlin
//                val intent34 = Intent(this,NuevaCompraActivity::class.java)
//                //iniciar el activity
//                this.startActivity(intent34)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}