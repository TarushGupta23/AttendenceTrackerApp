package com.example.gdsc_attendencetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.values

class adapterRV(private val counterList: ArrayList<CounterItem>) : RecyclerView.Adapter<adapterRV.CounterViewHolder>() {

    class CounterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val auth = FirebaseAuth.getInstance()
        val firebaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser!!.uid)
        val classNameTextView: TextView = itemView.findViewById(R.id.cardClassName)
        val attendenceTextView: TextView = itemView.findViewById(R.id.cardAttendence)
        val missedTextView: TextView = itemView.findViewById(R.id.cardMissed)
        val attendButton: Button = itemView.findViewById(R.id.cardAttendBtn)
        val missedButton: Button = itemView.findViewById(R.id.cardMissedBtn)
        val card:CardView = itemView.findViewById(R.id.counterCard)

        fun bind(counterItem: CounterItem) {
            classNameTextView.text = counterItem.name
            attendenceTextView.text = "${counterItem.attendedClasses}"
            missedTextView.text = "${counterItem.missedClasses}"

            attendButton.setOnClickListener {
                firebaseReference.child(counterItem.id!!).child("attendedClasses").setValue(counterItem.attendedClasses!! + 1)
            }

            missedButton.setOnClickListener {
                firebaseReference.child(counterItem.id!!).child("missedClasses").setValue(counterItem.missedClasses!! + 1)
            }

            card.setOnLongClickListener {
                showDeleteDialog(itemView.context, counterItem)
                true
            }
        }

        fun showDeleteDialog(context: Context, counterItem: CounterItem) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete") { _, _ ->
                    firebaseReference.child(counterItem.id!!).removeValue()
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.counter_card, parent, false)
        return CounterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return counterList.size
    }

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        holder.bind(counterList[position])
    }
}