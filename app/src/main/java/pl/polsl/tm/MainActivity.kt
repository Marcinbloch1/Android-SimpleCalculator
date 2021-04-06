package pl.polsl.tm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMinus = findViewById<Button>(R.id.minusButton)
        val buttonPlus = findViewById<Button>(R.id.plusButton)
        val textARe = findViewById<EditText>(R.id.aReText)
        val textAIm = findViewById<EditText>(R.id.aImText)
        val textBRe = findViewById<EditText>(R.id.bReText)
        val textBIm = findViewById<EditText>(R.id.bImText)

        val intent = Intent(this, MainActivity2::class.java)
        fun add(): String{
            val numARe = textARe.text.toString().toDoubleOrNull()
            val numAIm = textAIm.text.toString().toDoubleOrNull()
            val numBRe = textBRe.text.toString().toDoubleOrNull()
            val numBIm = textBIm.text.toString().toDoubleOrNull()

            var resultRe = numARe?.plus(numBRe!!)
            var resultIm = numAIm?.plus(numBIm!!)

            if (resultRe != null) {
                resultRe= Math.round(resultRe!!*10.0)/10.0
            }
            if (resultIm != null) {
                resultIm= Math.round(resultIm!!*10.0)/10.0
            }

            var result: String = ""
            if (resultIm != null) {
                if(resultIm < 0) {
                   result = resultRe.toString() + resultIm.toString() + "i"
                }
                else {
                    result = resultRe.toString() + "+" + resultIm.toString() + "i"
                }
            }


            intent.putExtra("Re", resultRe)
            intent.putExtra("Im", resultIm)
            return result
        }

        fun substract(): String {
            val numARe = textARe.text.toString().toDoubleOrNull()
            val numAIm = textAIm.text.toString().toDoubleOrNull()
            val numBRe = textBRe.text.toString().toDoubleOrNull()
            val numBIm = textBIm.text.toString().toDoubleOrNull()

            var resultRe = numARe?.minus(numBRe!!)
            var resultIm = numAIm?.minus(numBIm!!)

            if (resultRe != null) {
                resultRe= Math.round(resultRe!! *10.0)/10.0
            }
            if (resultIm != null) {
                resultIm= Math.round(resultIm!! *10.0)/10.0
            }

            var result: String = ""

            if (resultIm != null) {
                if (resultIm!! < 0) {
                    result = resultRe.toString() + resultIm.toString() + "i"
                } else {
                    result = resultRe.toString() + "+" + resultIm.toString() + "i"
                }
            }
            intent.putExtra("Re", resultRe)
            intent.putExtra("Im", resultIm)
            return result
        }

        buttonPlus.setOnClickListener {
            Toast.makeText(this, add(), Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        buttonMinus.setOnClickListener {
            Toast.makeText(this, substract(), Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }


        val spinnerList = resources.getStringArray(R.array.spinner_list)
        val spinner: Spinner = findViewById(R.id.spinner)
        if(spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if(spinnerList[position] == "+"){
                        Toast.makeText(this@MainActivity, add(), Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                    else if(spinnerList[position] == "-"){
                        Toast.makeText(this@MainActivity, substract(), Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //DO NOTHING
                }
            }
        }
    }
}