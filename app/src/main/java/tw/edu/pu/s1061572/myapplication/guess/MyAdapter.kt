package tw.edu.pu.s1061572.myapplication.guess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.s1061572.myapplication.R

class MyAdapter(private val historyData:ArrayList<History>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.listText.text = historyData[position].text
        if (historyData[position].correct)
            holder.listImg.setImageResource(R.drawable.check)
    }

    override fun getItemCount(): Int {
        return historyData.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listText:TextView = itemView.findViewById(R.id.listText)
        val listImg:ImageView = itemView.findViewById(R.id.listImg)
    }
}


