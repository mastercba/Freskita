package com.example.freskita.ui.produccion

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
import com.example.freskita.ui.compras.ComprasActivity
import org.json.JSONException
import org.json.JSONObject

class NuevaSiembraActivity : AppCompatActivity() {

    private lateinit var txtnameSiembra: EditText
    private lateinit var txtseedSiembra: EditText
    private lateinit var txtbjaSiembra: EditText
    private lateinit var txtcolorBjaSiembra: EditText
    private lateinit var btnGuardarSiembra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_siembra)

        txtnameSiembra = findViewById(R.id.nameSiembraET)
        txtseedSiembra = findViewById(R.id.seedSiembraET)
        txtbjaSiembra = findViewById(R.id.bjaSiembraET)
        txtcolorBjaSiembra = findViewById(R.id.colorBjaSiembraET)
        btnGuardarSiembra = findViewById(R.id.btnGuardarSiembra)

        btnGuardarSiembra.setOnClickListener() {
            addSiembra()
        }
    }

    private fun addSiembra() {
        //val nFecha = dateFechaCompra.text.toString()
        val nname = txtnameSiembra.text.toString()
        val nseed = txtseedSiembra.text.toString()
        val nbja = txtbjaSiembra.text.toString()
        val ncolorBja = txtcolorBjaSiembra.text.toString()

        if(nname.isEmpty()||nseed.isEmpty()||nbja.isEmpty()){
            Toast.makeText(this,"Please enter requiered field", Toast.LENGTH_SHORT).show()
        }else{
            val queue = Volley.newRequestQueue(this)
            val url : String = "https://quanticasoft.com/~quantid5/freskita/insertSiembra.php"
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
                    params.put("name", nname)
                    params.put("seed", nseed)
                    params.put("bja", nbja)
                    params.put("colorBja", ncolorBja)
                    return params
                }
            }
            //adding request to queue
            queue.add(stringRequest)
            //clearEditText()
            val back2Produccion = Intent(this, ProduccionActivity::class.java)
            this.startActivity(back2Produccion)
        }

    }

}