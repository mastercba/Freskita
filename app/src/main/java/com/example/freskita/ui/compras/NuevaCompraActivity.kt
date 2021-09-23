package com.example.freskita.ui.compras

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.freskita.R
import org.json.JSONException
import org.json.JSONObject

class NuevaCompraActivity : AppCompatActivity() {

    //private lateinit var dateFechaCompra: EditText
    private lateinit var txtDetalleCompra:EditText
    private lateinit var txtFacturaCompra:EditText
    private lateinit var txtMontoCompra:EditText
    private lateinit var btnGuardarCompra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_compra)

        txtDetalleCompra = findViewById(R.id.bjaRxTV)
        txtFacturaCompra = findViewById(R.id.almacigoDateRxTV)
        txtMontoCompra = findViewById(R.id.tuboDateRxTV)
        btnGuardarCompra = findViewById(R.id.btnGuardarCompra)

        //verificamos la conexion a INTERNET
        val conected = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val status = conected.activeNetworkInfo
        //
        btnGuardarCompra.setOnClickListener() {
//            if (status != null && status.isConnected) {
                addCompra()
//            } else {
//                Toast.makeText(this, "Revise su conexion a internet", Toast.LENGTH_LONG).show()
//            }
        }
    }

    private fun addCompra() {
        //val nFecha = dateFechaCompra.text.toString()
        val nDetalle = txtDetalleCompra.text.toString()
        val nFactura = txtFacturaCompra.text.toString()
        val nMonto = txtMontoCompra.text.toString()
        if(nDetalle.isEmpty()||nFactura.isEmpty()||nMonto.isEmpty()){
            Toast.makeText(this,"Please enter requiered field", Toast.LENGTH_SHORT).show()
        }else{
            val queue = Volley.newRequestQueue(this)
            val url : String = "https://quanticasoft.com/~quantid5/freskita/insertCompra.php"
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
                    //params.put("date", nFecha)
                    params.put("detalle", nDetalle)
                    params.put("factura", nFactura)
                    params.put("monto", nMonto)
                    return params
                }
            }
            //adding request to queue
            queue.add(stringRequest)
            clearEditText()
            // 1. creo el Intent
            val back2Compras = Intent(this, ComprasActivity::class.java)
            // 2. Inicializar la actividad con el Intent creado
            this.startActivity(back2Compras)

//            val queue = Volley.newRequestQueue(this)
//            val addUrl : String = "https://quanticasoft.com/~quantid5/freskita/insertCompra.php"
//
//            val stringRequest = StringRequest(Request.Method.POST, addUrl,
//                {
//
//                    //finish()
//                },
//                { error ->
//                    Toast.makeText(this, "Algo salio mal ${error}", Toast.LENGTH_LONG).show()
//                })
//            queue.add(stringRequest)

//            //check insert success or not success
//            if(status > -1){
//                Toast.makeText(this,"Student Added...", Toast.LENGTH_SHORT).show()
//                clearEditText()
//                //getStudents()
//            }else{
//                Toast.makeText(this,"Record not saved", Toast.LENGTH_SHORT).show()
//            }

        }

    }

    private fun clearEditText() {
        txtDetalleCompra.setText("")
        txtFacturaCompra.setText("")
        txtMontoCompra.setText("")
        txtMontoCompra.requestFocus()
    }
}