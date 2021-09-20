package com.example.freskita.ui.compras

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

class EditCompraActivity : AppCompatActivity() {

    private lateinit var txtUpdateIDCompra: TextView
    private lateinit var txtUpdateDateCompra: TextView
    private lateinit var txtUpdateDetalleCompra: TextView
    private lateinit var txtUpdateFacturaCompra: TextView
    private lateinit var txtUpdateMontoCompra: TextView
    private lateinit var btnUpdateCompra: Button

    lateinit var uId: String
    lateinit var uDate: String
    lateinit var uDetalle: String
    lateinit var uFactura: String
    lateinit var uMonto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_compra)

        txtUpdateIDCompra = findViewById(R.id.idRxTV)
        txtUpdateDateCompra = findViewById(R.id.fechaRxTV)
        txtUpdateDetalleCompra = findViewById(R.id.detalleRxET)
        txtUpdateFacturaCompra = findViewById(R.id.facturaRxET)
        txtUpdateMontoCompra = findViewById(R.id.montoRxET)
        btnUpdateCompra = findViewById(R.id.btnGuardarCompra)

        uId = intent.getStringExtra("iId").toString()
        uDate = intent.getStringExtra("iDate").toString()
        uDetalle = intent.getStringExtra("iDetalle").toString()
        uFactura = intent.getStringExtra("iFactura").toString()
        uMonto = intent.getStringExtra("iMonto").toString()

        txtUpdateIDCompra.text = uId
        txtUpdateDateCompra.text = uDate
        txtUpdateDetalleCompra.text = uDetalle
        txtUpdateFacturaCompra.text = uFactura
        txtUpdateMontoCompra.text = uMonto

        // Click Boton UPDATE
        btnUpdateCompra.setOnClickListener() {
            updateCompra()
        }
    }

    private fun updateCompra() {
        val uId = txtUpdateIDCompra.text.toString()
        val uDetalle = txtUpdateDetalleCompra.text.toString()
        val uFactura = txtUpdateFacturaCompra.text.toString()
        val uMonto = txtUpdateMontoCompra.text.toString()
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
}