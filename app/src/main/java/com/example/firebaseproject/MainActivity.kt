package com.example.firebaseproject

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity(){
    private lateinit var employeeNameEdt: EditText
    private lateinit var employeePhoneEdt:EditText
    private lateinit var employeeAddressEdt:EditText
    private lateinit var sendDatabtn: Button
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    lateinit var employeeInfo: EmployeeInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inits()
    }

    private fun inits() {
        // initializing our edittext and button
        employeeNameEdt = findViewById(R.id.idEdtEmployeeName)
        employeePhoneEdt = findViewById(R.id.idEdtEmployeePhoneNumber)
        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress)
        sendDatabtn = findViewById(R.id.idBtnSendData)

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase!!.getReference("EmployeeInfo");

        sendDatabtn.setOnClickListener {
            val name = employeeNameEdt.getText().toString()
            val phone = employeePhoneEdt.getText().toString()
            val address = employeeAddressEdt.getText().toString()

            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
                Toast.makeText(this@MainActivity, "Please add some data.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                addDatatoFirebase(name, phone, address)
            }
        }

        getData()

    }

    private fun getData() {
        //    databaseReference!!.addChildEventListener{}
    }

    private fun addDatatoFirebase(name: String, phone: String, address: String) {
        val employeeId = System.currentTimeMillis()
        employeeInfo = EmployeeInfo(employeeId,name, phone, address)
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                databaseReference!!.child(name+employeeId).setValue(employeeInfo)
                Toast.makeText(this@MainActivity, "data added", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }
}