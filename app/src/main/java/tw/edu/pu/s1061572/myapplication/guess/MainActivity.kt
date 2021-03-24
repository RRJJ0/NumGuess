package tw.edu.pu.s1061572.myapplication.guess

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.s1061572.myapplication.R
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private lateinit var userInput:EditText
    private lateinit var guess:Button
    private lateinit var img:ImageView
    private lateinit var history:RecyclerView
    private var randNum:String = ""
    private var check = BooleanArray(4)
    private var historyData:ArrayList<History> = ArrayList()
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userInput = findViewById(R.id.inputNum)
        guess = findViewById(R.id.guess)
        history = findViewById(R.id.history)
        img = findViewById(R.id.imageView)

        img.setImageResource(R.drawable.num)
        img.scaleType = ImageView.ScaleType.FIT_XY
        img.alpha = 0.2F
        randNum = (Math.random() * 10000).roundToInt().toString().padStart(4, '0')
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        myAdapter = MyAdapter(historyData)
        setAdapter()
        Log.d("TAG", "onCreate: $randNum")
        guess.setOnClickListener {
            val m: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            m.hideSoftInputFromWindow(userInput.windowToken, 0)
            if (guess.text == "重置"){
                randNum = (Math.random() * 10000).roundToInt().toString().padStart(4, '0')
                historyData.clear()
                guess.text = "猜看看"
                myAdapter.notifyDataSetChanged()
            }else
                judge(userInput.text.toString())
        }

    }


    private fun setAdapter(){
        history.adapter = myAdapter
        history.layoutManager = LinearLayoutManager(this)
    }

    private fun judge(input: String){
        check.fill(false)
        var index = 0
        var a = 0
        var b = 0
        for ( i in randNum ){
            if ( input.indexOf(i, index, false) == index ){
                a++
                check[index] = true
            } else{
                var indexForUserInput = 0
                for (n in input){
                    if (!check[indexForUserInput]&& n== i) {
                        b++
                        break
                    }
                    indexForUserInput++
                }
            }
            index ++
        }
        var isCorrect = false
        if ( a==4 ){
            isCorrect = true
            guess.text = "重置"
        }
        historyData.add(History(isCorrect, input + "is " + a + "A" + b + "B"))
        setAdapter()
        userInput.setText("")
        myAdapter.notifyDataSetChanged()
    }



}