package com.example.freskita.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freskita.R
import com.example.freskita.model.ComprasModel


class ComprasAdapter(val arrayList: ArrayList<ComprasModel>, val context: Context) :
    RecyclerView.Adapter<ComprasAdapter.ComprasViewHolder> (){

    // este metodo devuelve un ProductionViewHolder!!!!
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ComprasViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.compra_item,parent,false)
        return ComprasViewHolder(vista)
    }

    // enlaza la data con la vista o el modelo con la parte visual
    override fun onBindViewHolder(holder: ComprasViewHolder, position: Int) {
        // tengo una noticia en concreto
        val model = arrayList.get(position)
        //holder.idTextView.text = model.id.toString()
        holder.fechaCompraTV.text = model.date.toString()
        holder.desCompraTV.text = model.detalle
        holder.facturaCompraTV.text = model.factura
        holder.montoCompraTV.text = model.monto.toString()

//        holder.itemView.setOnClickListener{
//            //seleccionar el item de la posicion
//            val gmodel = arrayList.get(position)
//            //obtener el detalle de siembra seleccionada de un item con intent
//            var gId:Int = gmodel.id
//            var gCode: Int = gmodel.code
//            var gName: String = gmodel.name
//            var gSeed: String = gmodel.seed
//            var gDays: Int = gmodel.days
//            var gBja: Int = gmodel.bja
//            var gSiembraDate: String = gmodel.siembraDate
//            var gGDays: Int = gmodel.gDays
//            var gAlmacigoDate: String = gmodel.almacigoDate
//            var gADays: Int = gmodel.aDays
//            var gTuboDate: String = gmodel.tuboDate
//            var gTDays: Int = gmodel.tDays
//            var gCosechaDate: String = gmodel.cosechaDate
//
//            //crear intent con kotlin
//
//            val intentDetalle = Intent(context, EditActivity::class.java)
//            //poner todos los items con putExtra intent
//            intentDetalle.putExtra("iId",gId)
//            intentDetalle.putExtra("iCode",gCode)
//            intentDetalle.putExtra("iName",gName)
//            intentDetalle.putExtra("iSeed",gSeed)
//            intentDetalle.putExtra("iDays",gDays)
//            intentDetalle.putExtra("iBja",gBja)
//            intentDetalle.putExtra("iSiembraDate",gSiembraDate)
//            intentDetalle.putExtra("iGDays",gGDays)
//            intentDetalle.putExtra("iAlmacigoDate",gAlmacigoDate)
//            intentDetalle.putExtra("iADays",gADays)
//            intentDetalle.putExtra("iTuboDate",gTuboDate)
//            intentDetalle.putExtra("iTDays",gTDays)
//            intentDetalle.putExtra("iCosechaDate",gCosechaDate)
//
//            //iniciar el activity detalle
//            //startActivity(intent)
//            context.startActivity(intentDetalle)
//
//        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    //clase enbebida esta clase es el que sostiene la vista  ViewHolder
    inner class ComprasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //var idTextView: TextView
        var fechaCompraTV: TextView
        var desCompraTV: TextView
        var facturaCompraTV: TextView
        var montoCompraTV: TextView


        init {
            //idTextView = itemView.findViewById(R.id.idTextView)
            fechaCompraTV = itemView.findViewById(R.id.fechaCompraTV)
            desCompraTV = itemView.findViewById(R.id.desCompraTV)
            facturaCompraTV = itemView.findViewById(R.id.facturaCompraTV)
            montoCompraTV = itemView.findViewById(R.id.montoCompraTV)
        }
    }

}