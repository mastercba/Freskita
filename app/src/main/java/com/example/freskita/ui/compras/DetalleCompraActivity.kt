package com.example.freskita.ui.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
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
    private lateinit var btnUpdateCompra: Button

    private lateinit var txtTitulo: TextView
    lateinit var eId: String
    lateinit var eDate: String
    lateinit var eDetalle: String
    lateinit var eFactura: String
    lateinit var eMonto: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_compra)

        txtTitulo = findViewById(R.id.detalleCompraTitulo)
        btnUpdateCompra = findViewById(R.id.btnUpdateCompra)

        txtIDCompra = findViewById(R.id.idRxTV)
        txtEDdateCompra = findViewById(R.id.fechaRxTV)
        txtEDdetalleCompra = findViewById(R.id.bjaRxTV)
        txtEDfacturaCompra = findViewById(R.id.almacigoDateRxTV)
        txtEDmontoCompra = findViewById(R.id.tuboDateRxTV)


        eId = intent.getStringExtra("iId").toString()
        eDate = intent.getStringExtra("iDate").toString()
        eDetalle = intent.getStringExtra("iDetalle").toString()
        eFactura = intent.getStringExtra("iFactura").toString()
        eMonto = intent.getStringExtra("iMonto").toString()

        txtIDCompra.text = eId.toString()
        txtEDdateCompra.text = eDate.toString()
        txtEDdetalleCompra.text = eDetalle
        txtEDfacturaCompra.text = eFactura
        txtEDmontoCompra.text = eMonto

        //btnUpdateCompra.setOnClickListener { updateCompra() }

//        btnUpdateCompra.setOnClickListener() {
//            updateCompra()
//        }

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


    //<!---------------UpDate Compra------------------------------------->
    private fun updateCompra() {

        val uId = txtIDCompra.text.toString()
        val uDetalle = txtEDdetalleCompra.text.toString()
        val uFactura = txtEDfacturaCompra.text.toString()
        val uMonto = txtEDmontoCompra.text.toString()
        val queue = Volley.newRequestQueue(this)
        val url : String = "https://quanticasoft.com/~quantid5/freskita/editarCompra.php"
        //creating volley string request
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", uId)
                //params.put("date", nFecha)
                params.put("detalle", uDetalle)
                params.put("factura", uFactura)
                params.put("monto", uMonto)
                return params
            }
        }
        //adding request to queue
        queue.add(stringRequest)
        // 1. creo el Intent
        val back2Compras = Intent(this, ComprasActivity::class.java)
        // 2. Inicializar la actividad con el Intent creado
        this.startActivity(back2Compras)
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
                val udintent = Intent(this,EditCompraActivity::class.java)
                udintent.putExtra("iId",eId)
                udintent.putExtra("iDate",eDate)
                udintent.putExtra("iDetalle",eDetalle)
                udintent.putExtra("iFactura",eFactura)
                udintent.putExtra("iMonto",eMonto)
                this.startActivity(udintent)
//                //updateCompra()
//                txtTitulo.text = "UPDATE COMPRA"
//                btnUpdateCompra.visibility = View.VISIBLE
//
//                //txtIDCompra.text = eId
//                //txtEDdateCompra.text = eDate
//
//                //txtEDdetalleCompra.text = eDetalle
//                txtEDdetalleCompra.setCursorVisible(true)
//                //txtEDdetalleCompra.setInputType(InputType.TYPE_CLASS_TEXT);
//                txtEDdetalleCompra.inputType
//                //txtEDdetalleCompra.setFocusable(true);
//                //txtEDdetalleCompra.setEnabled(true);
//                txtEDdetalleCompra.setClickable(true)
//                txtEDdetalleCompra.setFocusableInTouchMode(true)
//                txtEDdetalleCompra.requestFocus()
//
//                //txtEDfacturaCompra.text = eFactura
//                txtEDfacturaCompra.setCursorVisible(true)
//                txtEDfacturaCompra.setFocusable(true)
//                txtEDfacturaCompra.setEnabled(true)
//                txtEDfacturaCompra.setClickable(true)
//                txtEDfacturaCompra.setFocusableInTouchMode(true)
//                txtEDfacturaCompra.requestFocus()
//
//                //txtEDmontoCompra.text = eMonto
//                txtEDmontoCompra.setCursorVisible(true)
//                txtEDmontoCompra.setFocusable(true)
//                txtEDmontoCompra.setEnabled(true)
//                txtEDmontoCompra.setClickable(true)
//                txtEDmontoCompra.setFocusableInTouchMode(true)
//                txtEDmontoCompra.requestFocus()

//                btnUpdateCompra.setOnClickListener() {
//                    updateCompra()
//                }
            }
            R.id.delete -> {
                delCompra()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}