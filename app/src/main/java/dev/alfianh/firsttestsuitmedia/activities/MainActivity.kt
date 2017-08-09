package dev.alfianh.firsttestsuitmedia.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dev.alfianh.firsttestsuitmedia.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNext.setOnClickListener {
            if (etName.text.isEmpty())
                etName.error = "Harus diisi !"
            else
                MenuActivity.start(this, etName.text.toString())
        }
    }
}
