package com.example.freskita.ui.produccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.freskita.R
import com.example.freskita.ui.compras.ComprasActivity
import com.example.freskita.ui.compras.EditCompraActivity
import org.json.JSONException
import org.json.JSONObject

class DetalleSiembraActivity : AppCompatActivity() {

    private lateinit var txtIDSiembra: TextView
    private lateinit var txtnameSiembra: TextView
    private lateinit var txtseedSiembra: TextView
    private lateinit var txtbjaSiembra: TextView
    private lateinit var txtcolorBjaSiembra: TextView
    private lateinit var txtsiembraDateSiembra: TextView
    private lateinit var txtalmacigoDateSiembra: TextView
    private lateinit var txttuboDateSiembra: TextView
    private lateinit var txtcosechaDateSiembra: TextView


    lateinit var eId: String
    lateinit var eName: String
    lateinit var eSeed: String
    lateinit var eBja: String
    lateinit var eColorBja: String
    lateinit var eSiembraDate: String
    lateinit var eAlmacigoDate: String
    lateinit var eTuboDate: String
    lateinit var eCosechaDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_siembra)

        //txtIDSiembra = findViewById(R.id.idRxTV)
        txtnameSiembra = findViewById(R.id.nameRxTV)
        txtseedSiembra = findViewById(R.id.semillaRxTV)
        txtbjaSiembra = findViewById(R.id.bjaRxTV)
        txtcolorBjaSiembra = findViewById(R.id.colorBjaRxTV)
        txtsiembraDateSiembra = findViewById(R.id.siembraDateRxTV)
        txtalmacigoDateSiembra = findViewById(R.id.almacigoDateRxTV)
        txttuboDateSiembra = findViewById(R.id.tuboDateRxTV)
        txtcosechaDateSiembra = findViewById(R.id.cosechaDateRxTV)

        eId = intent.getStringExtra("iId").toString()
        //eCode = intent.getStringExtra("iCode").toString()
        eName = intent.getStringExtra("iName").toString()
        eSeed = intent.getStringExtra("iSeed").toString()
        eBja = intent.getStringExtra("iBja").toString()
        eColorBja = intent.getStringExtra("iColorBja").toString()
        //eDays = intent.getStringExtra("iDays").toString()
        eSiembraDate = intent.getStringExtra("iSiembraDate").toString()
        eAlmacigoDate = intent.getStringExtra("iAlmacigoDate").toString()
        eTuboDate = intent.getStringExtra("iTuboDate").toString()
        eCosechaDate = intent.getStringExtra("iCosechaDate").toString()
       // eGDays = intent.getStringExtra("iGDays").toString()
        //eADays = intent.getStringExtra("iADays").toString()
        //eTDays = intent.getStringExtra("iTDays").toString()

        //txtIDCompra.text = eId.toString()
        txtnameSiembra.text = eName.toString()
        txtseedSiembra.text = eSeed
        txtbjaSiembra.text = eBja
        txtcolorBjaSiembra.text = eColorBja
        txtsiembraDateSiembra.text = eSiembraDate
        txtalmacigoDateSiembra.text = eAlmacigoDate
        txttuboDateSiembra.text = eTuboDate
        txtcosechaDateSiembra.text = eCosechaDate

    }

    //<!---------------Delete Compra------------------------------------->
    private fun delSiembra() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){ dialog,_->

            val delId = txtIDSiembra.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url: String = "https://quanticasoft.com/~quantid5/freskita/eliminarSiembra.php"
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

            val back2Produccion = Intent(this, ProduccionActivity::class.java)
            this.startActivity(back2Produccion)

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
//                val udintent = Intent(this, EditCompraActivity::class.java)
//                udintent.putExtra("iId",eId)
//                udintent.putExtra("iDate",eDate)
//                udintent.putExtra("iDetalle",eDetalle)
//                udintent.putExtra("iFactura",eFactura)
//                udintent.putExtra("iMonto",eMonto)
//                this.startActivity(udintent)
            }
            R.id.delete -> {
                delSiembra()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}