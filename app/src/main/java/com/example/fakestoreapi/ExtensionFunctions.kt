package com.example.fakestoreapi

import android.app.Activity
import android.content.Intent
import android.view.View

fun View.beGone(){
    this.visibility = View.GONE
}

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun Activity.startNewActivity(myClass:Class<*>?){
   startActivity(Intent(this,myClass))
    this.finish()
}