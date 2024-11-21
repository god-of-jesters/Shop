package com.example.shop

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.shop.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sp = arrayListOf(false, false, false, false)
        var zak = arrayListOf("", "", "", "")
        var cur = 0
        var salfetka = "не полагается"
        var chay = 0
        var text: String = "Вы заказали "
        var c = ""

        val data: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        data.button.setOnClickListener {
            text = "Вы заказали"
            zak = arrayListOf("", "", "", "")
            sp[0] = data.c1.isChecked
            sp[1] = data.c2.isChecked
            sp[2] = data.c3.isChecked
            sp[3] = data.c4.isChecked

            if ((data.radio[0] as RadioButton).isChecked){
                cur = 0
            }else if((data.radio[1] as RadioButton).isChecked){
                cur = 1
            }else{
                cur = 2
            }

            if(data.sc.isChecked) salfetka = "в комплекте" else salfetka = "не в комплекте"
            c = data.editText.text.toString()
            if(c!="") chay = data.editText.text.toString().toInt()

            if(sp[0] || sp[1] || sp[2] || sp[3]){
                if(sp[0]) zak[0] = "Сахар"
                if(sp[1]) zak[1] = "Соль"
                if(sp[2]) zak[2] = "Греча"
                if(sp[3]) zak[3] = "Курочка"

                for (i:String in zak) if(i != "") text += i + ", "

                when(cur){
                    0 -> text += "можете забрать из магазина"
                    1 -> text += "вам привезет его курьер"
                    2 -> text += "вам привезет его собака"
                }

                text += ", салфетка $salfetka"

                Snackbar.make(data.root, text, Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(data.root, "Вы ничего не заказали", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}