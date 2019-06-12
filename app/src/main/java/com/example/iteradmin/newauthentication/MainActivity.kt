package com.example.iteradmin.newauthentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= FirebaseDatabase.getInstance()
        val name=findViewById<EditText>(R.id.name)
        val col=findViewById<EditText>(R.id.college)
        val cty=findViewById<EditText>(R.id.city)
        val br=findViewById<EditText>(R.id.branch)
        val sav=findViewById<Button>(R.id.save1)
        val show=findViewById<TextView>(R.id.text12)

        sav.setOnClickListener{
            val data:String=name.text.toString()
            val data2:String=col.text.toString()
            val data3:String=cty.text.toString()
            val data4:String=br.text.toString()
            val ref:DatabaseReference=database.getReference("user").child("madhav")
            ref.child("name").setValue(data)
            ref.child("College").setValue(data2)
            ref.child("City").setValue(data3)
            ref.child("Branch").setValue(data4)
        }
        val ref= database.getReference("user").child("madhav")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
           //     val str=p0.getValue(String::class.java)
            //    show.text=str
                // val child:String ?=p0.child("name").getValue(String::class.java)
                // show.text =child
                val children=p0.children
                val str=StringBuilder()
                for (child in children){
                    str.append(child.key+":"+child.getValue(String::class.java)+"\n")
                }
                show.text =str


            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext,"value not found",Toast.LENGTH_SHORT).show()
            }
        })

    }
}
