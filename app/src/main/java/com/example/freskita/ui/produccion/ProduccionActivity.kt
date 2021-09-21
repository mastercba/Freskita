package com.example.freskita.ui.produccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.freskita.R
import com.example.freskita.adapter.ProduccionAdapter
import com.example.freskita.model.ProduccionModel
import org.json.JSONArray
import org.json.JSONObject

class ProduccionActivity : AppCompatActivity() {

    val arrayList = ArrayList<ProduccionModel>()
    val displayList = ArrayList<ProduccionModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produccion)

        getSiembras()

    }

    private fun getSiembras(){
        /*----Codigo para extraer los datos desde mysql----*/
        val queue = Volley.newRequestQueue(this)
        val url="https://quanticasoft.com/~quantid5/freskita/listaSiembras.php"

        val stringRequest = StringRequest(Request.Method.GET,url, { response ->
            val jsonArray = JSONArray(response)

            for (i in 0 until jsonArray.length()){
                val jsonObject = JSONObject(jsonArray.getString(i))
                var id = jsonObject.get("id").toString()
                var code = jsonObject.get("code").toString().trim()
                var name = jsonObject.get("name").toString()
                var seed = jsonObject.get("seed").toString()
                var days = jsonObject.get("days").toString()
                var bja = jsonObject.get("bja").toString()
                var colorBja = jsonObject.get("colorBja").toString()
                var siembraDate = jsonObject.get("siembraDate").toString()
                var gDays = jsonObject.get("gDays").toString()
                var almacigoDate = jsonObject.get("almacigoDate").toString()
                var aDays = jsonObject.get("aDays").toString()
                var tuboDate = jsonObject.get("tuboDate").toString()
                var tDays = jsonObject.get("tDays").toString()
                var cosechaDate = jsonObject.get("cosechaDate").toString()

                //Toast.makeText(this,"ID: "+id+" CODE: "+code+
                //        " Name: "+name+" Seed: "+seed, Toast.LENGTH_LONG).show()
                //Toast.makeText(this,"Update Failed", Toast.LENGTH_SHORT).show()
                arrayList.add(ProduccionModel(id,code,name,seed,days,bja,
                    colorBja,siembraDate,gDays,almacigoDate,aDays,tuboDate,
                    tDays,cosechaDate))
            }
            displayList.addAll(arrayList)
            //recyclerView.adapter!!.notifyDataSetChanged()

            val recyclerView = findViewById<RecyclerView>(R.id.produccionRecyclerView)
            //val pAdapter = ProductionAdapter()....working!
            val pAdapter = ProduccionAdapter(displayList,this)

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = pAdapter

        }, Response.ErrorListener{ error ->
            Toast.makeText(this,"Algo salio mal ${error}", Toast.LENGTH_LONG).show()
        })
        queue.add(stringRequest)
        //displayList.addAll(arrayList)
    }

    //------------------------------------------------------------------->
    //<!-----AppBar Menu, Botones:siembra ------------------------------->
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_siembras,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ingresar -> {
                val go2NuevaSiembra = Intent(this, NuevaSiembraActivity::class.java)
                this.startActivity(go2NuevaSiembra)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}