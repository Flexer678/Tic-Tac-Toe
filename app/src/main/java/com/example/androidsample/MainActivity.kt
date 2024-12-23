package com.example.androidsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.log

val buttons = mutableMapOf(
    R.id.b1 to -1,
    R.id.b2 to -1,
    R.id.b3 to -1,
    R.id.b4 to -1,
    R.id.b5 to -1,
    R.id.b6 to -1,
    R.id.b7 to -1,
    R.id.b8 to -1,
    R.id.b9 to -1
)

val horizontalWin = listOf(
    listOf(R.id.b1, R.id.b2, R.id.b3),
    listOf(R.id.b4, R.id.b5, R.id.b6),
    listOf(R.id.b7, R.id.b8, R.id.b9)
)

val verticalWin = listOf(
    listOf(R.id.b1, R.id.b4, R.id.b7),
    listOf(R.id.b2, R.id.b5, R.id.b8),
    listOf(R.id.b3, R.id.b6, R.id.b9)
)

val diagonalPositiveWin = listOf(
    listOf(R.id.b1, R.id.b5, R.id.b9)
)

val diagonalNegativeWin = listOf(
    listOf(R.id.b3, R.id.b5, R.id.b7)
)
val Title: Int = R.id.title
val X : String =  "x"
val O : String  = "O"
var player: String = X;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }




    }

    fun checkWin(i: Int): Int {


        for (listItem in horizontalWin) {
            Log.d("X",buttons[listItem[0]].toString())
            if (buttons[listItem[0]] == i && buttons[listItem[1]] == i && buttons[listItem[2]] == i) {
                return 1
            }
        }

        for (listItem in verticalWin) {
            if (buttons[listItem[0]] == i && buttons[listItem[1]] == i && buttons[listItem[2]] == i) {
                return 1
            }
        }

        for (listItem in diagonalNegativeWin) {
            if (buttons[listItem[0]] == i && buttons[listItem[1]] == i && buttons[listItem[2]] == i) {
                return 1
            }
        }

        for (listItem in diagonalPositiveWin) {
            if (buttons[listItem[0]] == i && buttons[listItem[1]] == i && buttons[listItem[2]] == i) {
                return 1
            }
        }

        return 0
    }




    @SuppressLint("SetTextI18n")
    fun makeDisable(v: View){
        val titleTextView = findViewById<TextView>(Title)

        v.isEnabled = false
        if(player == "x"){
           findViewById<Button>(v.id).text = X;
           player = O
            buttons[v.id] = 1

            if(checkWin(1) == 1){ titleTextView.text = "X wins "; resseter()}
        }else{
            findViewById<Button>(v.id).text = O
            player = X
            buttons[v.id] = 0

            if(checkWin(0) == 1){ titleTextView.text = "O wins "; resseter() }
        }





    }


    fun resseter(){
        for (kvalue in buttons.keys){
            findViewById<Button>(kvalue).isEnabled = false
        }
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
               resetHandler()
            }, 2000
        )
    }

    //handling updating


    fun resetHandler(){
        val titleTextView = findViewById<TextView>(Title)

        for (kvalue in buttons.keys){
            findViewById<Button>(kvalue).isEnabled = true
            buttons[kvalue] = -1
            findViewById<Button>(kvalue).text = "-"

        }
        titleTextView.text = "Tic Tac Toe"
    }

    fun resetHandle(v: View){
        val titleTextView = findViewById<TextView>(Title)

        for (kvalue in buttons.keys){
            findViewById<Button>(kvalue).isEnabled = true
            buttons[kvalue] = -1
            findViewById<Button>(kvalue).text = "-"

        }
        titleTextView.text = "Tic Tac Toe"
    }





}