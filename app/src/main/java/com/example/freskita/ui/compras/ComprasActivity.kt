package com.example.freskita.ui.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.freskita.R
import com.example.freskita.adapter.ComprasAdapter
import com.example.freskita.model.ComprasModel
import org.json.JSONArray
import org.json.JSONObject

class ComprasActivity : AppCompatActivity() {

    lateinit var FAB_btnNewCompra: Button

    //creamos un ArrayList vacio de tipo ProductionModel, ():constructor
    val arrayList = ArrayList<ComprasModel>()
    val displayList = ArrayList<ComprasModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)

        //getSiembras()
        /*----Codigo para extraer los datos desde mysql----*/
        val queue = Volley.newRequestQueue(this)
        val url="https://quanticasoft.com/~quantid5/freskita/listaCompras.php"

        val stringRequest = StringRequest(Request.Method.GET,url, { response ->
            val jsonArray = JSONArray(response)

            for (i in 0 until jsonArray.length()){
                val jsonObject = JSONObject(jsonArray.getString(i))
                var id = jsonObject.get("id").toString().toInt()
                var date = jsonObject.get("date").toString().trim()
                var detalle = jsonObject.get("detalle").toString()
                var factura = jsonObject.get("factura").toString()
                var monto = jsonObject.get("monto").toString().toDouble()
                //Toast.makeText(this,"ID: "+id+" CODE: "+code+
                //        " Name: "+name+" Seed: "+seed, Toast.LENGTH_LONG).show()
                arrayList.add(ComprasModel(id,date,detalle,factura,monto))
            }
            displayList.addAll(arrayList)
            //recyclerView.adapter!!.notifyDataSetChanged()

            val recyclerView = findViewById<RecyclerView>(R.id.comprasRecyclerView)
            //val pAdapter = ProductionAdapter()....working!
            val cAdapter = ComprasAdapter(displayList,this)

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = cAdapter

        }, Response.ErrorListener{ error ->
            Toast.makeText(this,"Algo salio mal ${error}", Toast.LENGTH_LONG).show()
        })
        queue.add(stringRequest)
        //displayList.addAll(arrayList)

//        /*codigo para agregar un nuevo producto*/
//        FAB_btnNewCompra.setOnClickListener {
//            //crear intent con kotlin
//            //val intent = Intent(this,NuevaCompraActivity::class.java)
//            //iniciar el activity
//            //this.startActivity(intent)
//        }
//        /*-------------------------------------*/
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

    //<!-----AppBar Menu, Botones:produccion,compras,ventas & salir------>
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_compras,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ingresar -> {
            //crear intent con kotlin
            val intent34 = Intent(this,NuevaCompraActivity::class.java)
            //iniciar el activity
            this.startActivity(intent34)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //------------------------------------------------------------------->
}
