package com.example.gdsc_attendencetracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var addBtn:Button
    lateinit var logout:Button
    lateinit var recyclerView:RecyclerView

    lateinit var auth:FirebaseAuth
    lateinit var databaseReference:DatabaseReference
    lateinit var itemList:ArrayList<CounterItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        addBtn = findViewById(R.id.addButton)
        logout = findViewById(R.id.logOutButton)
        recyclerView = findViewById(R.id.recyclerView)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(user!!.uid)

        recyclerView.layoutManager = LinearLayoutManager(this)
        itemList = arrayListOf<CounterItem>()
        recyclerView.adapter = adapterRV(itemList)

        logout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        addBtn.setOnClickListener {
            showInputDialog()
        }
        fetchDataFromFirebase()
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_input, null)

        val editText1: EditText = dialogView.findViewById(R.id.editText1)
        val btnSaveDialog: Button = dialogView.findViewById(R.id.btnSave)

        val alertDialog = builder.setView(dialogView).create()

        btnSaveDialog.setOnClickListener {
            val inputText1 = editText1.text.toString()

            if (inputText1.isEmpty()) {
                Toast.makeText(this, "Please enter name of class", Toast.LENGTH_SHORT).show()
            } else {
                val store = databaseReference.push()
                val item = CounterItem(store.key, inputText1, 0, 0)
                store.setValue(item)
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }

    private fun fetchDataFromFirebase() {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val counterItem = dataSnapshot.getValue(CounterItem::class.java)
                        if (counterItem != null) {
                            itemList.add(counterItem)
                        }
                    }
                }

                recyclerView.adapter = adapterRV(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}