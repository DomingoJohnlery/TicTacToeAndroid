package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var turn = "X"
        var numOfTurn = 9
        val state = arrayListOf(arrayListOf("","",""),arrayListOf("","",""),arrayListOf("","",""))
        val buttons = arrayOf(
            binding.btnTL, binding.btnTM, binding.btnTR,
            binding.btnML, binding.btnMM, binding.btnMR,
            binding.btnBL, binding.btnBM, binding.btnBR)

        fun xo(){
            turn = if (turn == "X") "O" else "X"
            binding.tvTurn.text = "Turn $turn"
        }
        fun resetBoard(){
            for (i in buttons.indices){
                buttons[i].text = ""
                buttons[i].isClickable = true
            }
            numOfTurn = 9
            binding.tvTurn.text = "Turn $turn"
            state.forEach { row -> row.fill("")}
        }
        fun result(title: String) {
            AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("Reset") {
                        _,_ -> resetBoard()
                }
                .setCancelable(false)
                .show()
        }
        fun checkWin(symbol: String) {
            if (symbol == "X")
                result("X Wins!")
            else if (symbol == "O")
                result("O Wins!")
        }
        fun update() {
            numOfTurn--
            // Check rows
            for (i in 0 until 3)
                if (state[i][0] == state[i][1] && state[i][1] == state[i][2]) {
                    checkWin(state[i][0])
                    return
                }
            // Check columns
            for (j in 0 until 3)
                if (state[0][j] == state[1][j] && state[1][j] == state[2][j]) {
                    checkWin(state[0][j])
                    return
                }
            // Check diagonals
            if ((state[0][0] == state[1][1] && state[1][1] == state[2][2]) ||
                (state[0][2] == state[1][1] && state[1][1] == state[2][0])) {
                checkWin(state[1][1])
                return
            }

            if (numOfTurn == 0)
                result("Draw!")
        }

        for (i in buttons.indices){
            buttons[i].text = ""
        }

        binding.btnTL.setOnClickListener {
            binding.btnTL.text = turn
            binding.btnTL.isClickable = false
            state[0][0] = turn
            xo()
            update()
        }
        binding.btnTM.setOnClickListener {
            binding.btnTM.text = turn
            binding.btnTM.isClickable = false
            state[0][1] = turn
            xo()
            update()
        }
        binding.btnTR.setOnClickListener {
            binding.btnTR.text = turn
            binding.btnTR.isClickable = false
            state[0][2] = turn
            xo()
            update()
        }
        binding.btnML.setOnClickListener {
            binding.btnML.text = turn
            binding.btnML.isClickable = false
            state[1][0] = turn
            xo()
            update()
        }
        binding.btnMM.setOnClickListener {
            binding.btnMM.text = turn
            binding.btnMM.isClickable = false
            state[1][1] = turn
            xo()
            update()
        }
        binding.btnMR.setOnClickListener {
            binding.btnMR.text = turn
            binding.btnMR.isClickable = false
            state[1][2] = turn
            xo()
            update()
        }
        binding.btnBL.setOnClickListener {
            binding.btnBL.text = turn
            binding.btnBL.isClickable = false
            state[2][0] = turn
            xo()
            update()
        }
        binding.btnBM.setOnClickListener {
            binding.btnBM.text = turn
            binding.btnBM.isClickable = false
            state[2][1] = turn
            xo()
            update()
        }
        binding.btnBR.setOnClickListener {
            binding.btnBR.text = turn
            binding.btnBR.isClickable = false
            state[2][2] = turn
            xo()
            update()
        }
    }

}
