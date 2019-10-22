package com.example.sum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sum(view: View) {
        calculate()
    }

    private fun calculate() {
        val num1 = findViewById<EditText>(R.id.num1).text.toString()
        val num2 = findViewById<EditText>(R.id.num2).text.toString()
        val show = findViewById<TextView>(R.id.show)



        if (!isNumber(num1) || !isNumber(num2)) {
            val ss: HashMap<String, String> = hashMapOf()

            val numArray1 = num1.toCharArray()
            val numArray2 = num2.toCharArray()

            for (i in num1.indices) {
                if (ss.get(numArray1[i].toString()).isNullOrEmpty()) {
                    ss.put(numArray1[i].toString(), "1");
                } else {

                    var count = ss.get(numArray1[i].toString())?.toInt()?.plus(1)
                    ss.put(numArray1[i].toString(), count.toString())

                }
            }
            for (i in num2.indices) {
                if (ss.get(numArray2[i].toString()).isNullOrEmpty()) {
                    ss.put(numArray2[i].toString(), "1");
                } else {

                    var count = ss.get(numArray2[i].toString())?.toInt()?.plus(1)
                    ss.put(numArray2[i].toString(), count.toString())
                }
            }

            show.textSize = 25f
            show.setPadding(75, 0, 75, 0)
            var result = ss.toString();
            result = result.substring(1, result.length - 1)
            display(result)

        } else {
            if (num1.isNullOrBlank() || num2.isNullOrBlank()) {
                display("Invalid input")
                show.textSize = 25f
            }
//            else if(num1.length > 5 || num2.length > 5 ){
//                display("Out of range")
//            }
            else {
                try {
                    var result = (num1.toFloat() + num2.toFloat()).toString()
                    show.textSize = 50f
                    display(result)
                } catch (e: Exception) {
                    display(e.toString())
                }
            }
        }
    }

    private fun display(res: String) {
        val show = findViewById<TextView>(R.id.show)
        show.text = res
    }

    private fun isNumber(res: String): Boolean {
        val regex: Regex = Regex("(?:-{1})?(\\d+)(?:\\.{1}\\d+)?")
        return ({ input: CharSequence -> regex.matches(input) })(res)
    }
}