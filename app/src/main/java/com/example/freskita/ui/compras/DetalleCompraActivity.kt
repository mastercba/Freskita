package com.example.freskita.ui.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.freskita.R
import org.json.JSONException
import org.json.JSONObject

class DetalleCompraActivity : AppCompatActivity() {

    private lateinit var txtIDCompra: TextView
    private lateinit var txtEDdateCompra: TextView
    private lateinit var txtEDdetalleCompra: TextView
    private lateinit var txtEDfacturaCompra: TextView
    private lateinit var txtEDmontoCompra: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_compra)

        txtIDCompra = findViewById(R.id.idRxTV)
        txtEDdateCompra = findViewById(R.id.fechaRxTV)
        txtEDdetalleCompra = findViewById(R.id.detalleRxET)
        txtEDfacturaCompra = findViewById(R.id.facturaRxET)
        txtEDmontoCompra = findViewById(R.id.montoRxET)

        var eId = intent.getStringExtra("iId")
        var eDate = intent.getStringExtra("iDate")
        var eDetalle = intent.getStringExtra("iDetalle")
        var eFactura = intent.getStringExtra("iFactura")
        var eMonto = intent.getStringExtra("iMonto")

        txtIDCompra.text = eId.toString()
        txtEDdateCompra.text = eDate.toString()
        txtEDdetalleCompra.text = eDetalle
        txtEDfacturaCompra.text = eFactura
        txtEDmontoCompra.text = eMonto

    }


    //<!---------------Delete Compra------------------------------------->
    private fun delCompra() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){ dialog,_->

            val delId = txtIDCompra.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url: String = "https://quanticasoft.com/~quantid5/freskita/eliminarCompra.php"
            //creating volley string request
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(
                            applicationContext,
                            obj.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError ->
                    Toast.makeText(
                        applicationContext,
                        volleyError.message,
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    //params.put("date", nFecha)
                    params.put("id", delId)
                    return params
                }
            }
            //adding request to queue
            queue.add(stringRequest)

            // 1. creo el Intent
            val back2Compras = Intent(this, ComprasActivity::class.java)
            // 2. Inicializar la actividad con el Intent creado
            this.startActivity(back2Compras)

            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog,_->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()

    }
    //------------------------------------------------------------------->



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
                delCompra()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}