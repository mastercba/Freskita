package com.example.freskita.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freskita.R
import com.example.freskita.model.ProduccionModel
import kotlin.collections.ArrayList


class ProduccionAdapter(val arrayList: ArrayList<ProduccionModel>, val context: Context) :
    RecyclerView.Adapter<ProduccionAdapter.ProduccionViewHolder> (){

    // este metodo devuelve un ProductionViewHolder!!!!
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ProduccionViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.produccion_item,parent,false)
        return ProduccionViewHolder(vista)
    }

    // enlaza la data con la vista o el modelo con la parte visual
    override fun onBindViewHolder(holder: ProduccionViewHolder, position: Int) {
        // tengo una noticia en concreto
        val model = arrayList.get(position)
        holder.idSiembraTV.text = model.id.toString()
        holder.codeSiembraTV.text = model.code.toString()
        holder.nameSiembraTV.text = model.name
        holder.seedSiembraTV.text = model.seed
        holder.daysSiembraTV.text = model.days.toString()
        holder.bjaSiembraTV.text = model.bja.toString()
        holder.colorBjaSiembraTV.text = model.colorBja.toString()
        holder.siembradateSiembraTV.text = model.siembraDate.toString()
        holder.gdaysSiembraTV.text = model.gDays.toString()
        holder.almacigodateSiembraTV.text = model.almacigoDate.toString()
        holder.adaysSiembraTV.text = model.aDays.toString()
        holder.tubodateSiembraTV.text = model.tuboDate.toString()
        holder.tdaysSiembraTV.text = model.tDays.toString()
        holder.cosechadateSiembraTV.text = model.cosechaDate.toString()

        holder.itemView.setOnClickListener{
            //seleccionar el item de la posicion
            val gmodel = arrayList.get(position)
            //obtener el detalle de compra seleccionada de un item con intent
            var gId:String = gmodel.id
            var gcode: String = gmodel.code
            var gname: String = gmodel.name
            var gseed: String = gmodel.seed
            var gbja: String = gmodel.bja
            var gcolorBja: String = gmodel.colorBja
            var gdays: String = gmodel.days
            var gsiembraDate: String = gmodel.siembraDate
            var galmacigoDate: String = gmodel.almacigoDate
            var gtuboDate: String = gmodel.tuboDate
            var gcosechaDate: String = gmodel.cosechaDate
            var ggDays: String = gmodel.gDays
            var gaDays: String = gmodel.aDays
            var gtDays: String = gmodel.tDays

            //crear intent con kotlin

//            val intentComprasDetalle = Intent(context, DetalleCompraActivity::class.java)
            //poner todos los items con putExtra intent
//            intentComprasDetalle.putExtra("iId",gId)
//            intentComprasDetalle.putExtra("iDate",gDate)
//            intentComprasDetalle.putExtra("iDetalle",gDetalle)
//            intentComprasDetalle.putExtra("iFactura",gFactura)
//            intentComprasDetalle.putExtra("iMonto",gMonto)

            //iniciar el activity Comprasdetalle
//            context.startActivity(intentComprasDetalle)

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    //clase enbebida esta clase es el que sostiene la vista  ViewHolder
    inner class ProduccionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idSiembraTV: TextView
        var codeSiembraTV: TextView
        var nameSiembraTV: TextView
        var seedSiembraTV: TextView
        var bjaSiembraTV: TextView
        var colorBjaSiembraTV: TextView
        var daysSiembraTV: TextView
        var siembradateSiembraTV: TextView
        var almacigodateSiembraTV: TextView
        var tubodateSiembraTV: TextView
        var cosechadateSiembraTV: TextView
        var gdaysSiembraTV: TextView
        var adaysSiembraTV: TextView
        var tdaysSiembraTV: TextView

        init {
            idSiembraTV = itemView.findViewById(R.id.idSiembraTV)
            codeSiembraTV = itemView.findViewById(R.id.codeSiembraTV)
            nameSiembraTV = itemView.findViewById(R.id.nameSiembraTV)
            seedSiembraTV = itemView.findViewById(R.id.seedSiembraTV)
            bjaSiembraTV = itemView.findViewById(R.id.bjaSiembraTV)
            colorBjaSiembraTV = itemView.findViewById(R.id.colorBjaSiembraTV)
            daysSiembraTV = itemView.findViewById(R.id.daysSiembraTV)
            siembradateSiembraTV = itemView.findViewById(R.id.siembradateSiembraTV)
            almacigodateSiembraTV = itemView.findViewById(R.id.almacigodateSiembraTV)
            tubodateSiembraTV = itemView.findViewById(R.id.tubodateSiembraTV)
            cosechadateSiembraTV = itemView.findViewById(R.id.cosechadateSiembraTV)
            gdaysSiembraTV = itemView.findViewById(R.id.gdaysSiembraTV)
            adaysSiembraTV = itemView.findViewById(R.id.adaysSiembraTV)
            tdaysSiembraTV = itemView.findViewById(R.id.tdaysSiembraTV)
        }
    }

}