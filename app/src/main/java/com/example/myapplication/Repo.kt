package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
class Repo {
    fun getData(): LiveData<MutableList<Brand>> {
        val mutableData = MutableLiveData<MutableList<Brand>>()
        val database = Firebase.database

        val myRef = database.getReference("resData")
        myRef.addValueEventListener(object : ValueEventListener {
            val listData: MutableList<Brand> = mutableListOf<Brand>()
            override fun onDataChange(snapshot: DataSnapshot) {



                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Brand::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }
}