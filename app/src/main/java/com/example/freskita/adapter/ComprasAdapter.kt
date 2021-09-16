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
import com.example.freskita.ui.compras.DetalleCompraActivity
import java.util.*
import kotlin.collections.ArrayList


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
        holder.idCompraTV.text = model.id.toString()
        holder.fechaCompraTV.text = model.date.toString()
        holder.desCompraTV.text = model.detalle
        holder.facturaCompraTV.text = model.factura
        holder.montoCompraTV.text = model.monto.toString()

        holder.itemView.setOnClickListener{
            //seleccionar el item de la posicion
            val gmodel = arrayList.get(position)
            //obtener el detalle de compra seleccionada de un item con intent
            var gId:String = gmodel.id
            var gDate: String = gmodel.date
            var gDetalle: String = gmodel.detalle
            var gFactura: String = gmodel.factura
            var gMonto: String = gmodel.monto

            //crear intent con kotlin

            val intentComprasDetalle = Intent(context, DetalleCompraActivity::class.java)
            //poner todos los items con putExtra intent
            intentComprasDetalle.putExtra("iId",gId)
            intentComprasDetalle.putExtra("iDate",gDate)
            intentComprasDetalle.putExtra("iDetalle",gDetalle)
            intentComprasDetalle.putExtra("iFactura",gFactura)
            intentComprasDetalle.putExtra("iMonto",gMonto)

            //iniciar el activity Comprasdetalle
            context.startActivity(intentComprasDetalle)

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    //clase enbebida esta clase es el que sostiene la vista  ViewHolder
    inner class ComprasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idCompraTV: TextView
        var fechaCompraTV: TextView
        var desCompraTV: TextView
        var facturaCompraTV: TextView
        var montoCompraTV: TextView


        init {
            idCompraTV = itemView.findViewById(R.id.idCompraTV)
            fechaCompraTV = itemView.findViewById(R.id.fechaCompraTV)
            desCompraTV = itemView.findViewById(R.id.desCompraTV)
            facturaCompraTV = itemView.findViewById(R.id.facturaCompraTV)
            montoCompraTV = itemView.findViewById(R.id.montoCompraTV)
        }
    }

}