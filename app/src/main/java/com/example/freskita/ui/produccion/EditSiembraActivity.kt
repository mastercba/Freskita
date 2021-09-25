package com.example.freskita.ui.produccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

class EditSiembraActivity : AppCompatActivity() {

    private lateinit var txtUpdateIDSiembra: TextView
    private lateinit var txtUpdateNameSiembra: TextView
    private lateinit var txtUpdateSeedSiembra: TextView
    private lateinit var txtUpdateBjaSiembra: TextView
    private lateinit var txtUpdateColorBjaSiembra: TextView
    private lateinit var txtUpdateSiembraDateSiembra: TextView
    private lateinit var txtUpdateAlmacigoDateSiembra: TextView
    private lateinit var txtUpdateTuboDateSiembra: TextView
    private lateinit var txtUpdateCosechaDateSiembra: TextView

    private lateinit var btnUpdateSiembra: Button

    lateinit var uId: String
    lateinit var uName: String
    lateinit var uSeed: String
    lateinit var uBja: String
    lateinit var uColorBja: String
    lateinit var uSiembraDate: String
    lateinit var uAlmacigoDate: String
    lateinit var uTuboDate: String
    lateinit var uCosechaDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_siembra)

        //txtUpdateIDSiembra = findViewById(R.id.idRxET)
        txtUpdateNameSiembra = findViewById(R.id.nameRxET)
        txtUpdateSeedSiembra = findViewById(R.id.seedRxET)
        txtUpdateBjaSiembra = findViewById(R.id.bjaRxET)
        txtUpdateColorBjaSiembra = findViewById(R.id.colorBjaRxET)
        txtUpdateSiembraDateSiembra = findViewById(R.id.siembraDateRxET)
        txtUpdateAlmacigoDateSiembra = findViewById(R.id.almacigoDateRxET)
        txtUpdateTuboDateSiembra = findViewById(R.id.tuboDateRxET)
        txtUpdateCosechaDateSiembra = findViewById(R.id.cosechaDateRxET)

        btnUpdateSiembra = findViewById(R.id.btnGuardarSiembra)

        uId = intent.getStringExtra("iId").toString()
        uName = intent.getStringExtra("iName").toString()
        uSeed = intent.getStringExtra("iSeed").toString()
        uBja = intent.getStringExtra("iBja").toString()
        uColorBja = intent.getStringExtra("iColorBja").toString()
        uSiembraDate = intent.getStringExtra("iSiembraDate").toString()
        uAlmacigoDate = intent.getStringExtra("iAlmacigoDate").toString()
        uTuboDate = intent.getStringExtra("iTuboDate").toString()
        uCosechaDate = intent.getStringExtra("iCosechaDate").toString()

        txtUpdateNameSiembra.text = uName
        txtUpdateSeedSiembra.text = uSeed
        txtUpdateBjaSiembra.text = uBja
        txtUpdateColorBjaSiembra.text = uColorBja
        txtUpdateSiembraDateSiembra.text = uSiembraDate
        txtUpdateAlmacigoDateSiembra.text = uAlmacigoDate
        txtUpdateTuboDateSiembra.text = uTuboDate
        txtUpdateCosechaDateSiembra.text = uCosechaDate

        // Click Boton UPDATE
        btnUpdateSiembra.setOnClickListener() {
            updateSiembra()
        }

    }

    private fun updateSiembra() {
        //val uId = txtUpdateIDCompra.text.toString()
        val uName = txtUpdateNameSiembra.text.toString()
        val uSeed = txtUpdateSeedSiembra.text.toString()
        val uBja = txtUpdateBjaSiembra.text.toString()
        val uColorBja = txtUpdateColorBjaSiembra.text.toString()
        val uSiembraDate = txtUpdateSiembraDateSiembra.text.toString()
        val uAlmacigoDate = txtUpdateAlmacigoDateSiembra.text.toString()
        val uTuboDate = txtUpdateTuboDateSiembra.text.toString()
        val uCosechaDate = txtUpdateCosechaDateSiembra.text.toString()

        val queue = Volley.newRequestQueue(this)
        val url : String = "https://quanticasoft.com/~quantid5/freskita/editarSiembra.php"
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
                params.put("name", uName)
                params.put("seed", uSeed)
                params.put("bja", uBja)
                params.put("colorBja", uColorBja)
                params.put("siembraDate", uSiembraDate)
                params.put("almacigoDate", uAlmacigoDate)
                params.put("tuboDate", uTuboDate)
                params.put("cosechaDate", uCosechaDate)
                return params
            }
        }
        //adding request to queue
        queue.add(stringRequest)
        val back2Produccion = Intent(this, ProduccionActivity::class.java)
        this.startActivity(back2Produccion)
    }
}