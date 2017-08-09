package dev.alfianh.firsttestsuitmedia.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dev.alfianh.firsttestsuitmedia.R
import kotlinx.android.synthetic.main.activity_menu.*

/**
 * Created by alfianh on 8/9/17.
 */
class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        etName.text = "Name : " + intent.getStringExtra("name")
        btnEvent.setOnClickListener { EventActivity.start(this) }
        btnGuest.setOnClickListener { GuestActivity.start(this) }
    }

    companion object {
        const val ACTIVITY_REQUEST_CODE = 0
        const val EVENT_RESULT_CODE = 1000
        const val GUEST_RESULT_CODE = 2000

        fun start(context: Context, name: String) {
            context.startActivity(Intent(context, MenuActivity::class.java).putExtra("name", name))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTIVITY_REQUEST_CODE) {
            when (resultCode) {
                EVENT_RESULT_CODE -> btnEvent.text = data?.getStringExtra("event")
                GUEST_RESULT_CODE -> run {
                    btnGuest.text = data?.getStringExtra("guest")
                    val birthdate = data?.getStringExtra("birthdate")
                    val day: Int = Integer.parseInt(birthdate!!.split("-")[2])
                    if (day % 2 == 0 && day % 3 == 0) {
                        Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show()
                    } else if (day % 2 == 0) {
                        Toast.makeText(this, "blackberry", Toast.LENGTH_SHORT).show()
                    } else if (day % 3 == 0) {
                        Toast.makeText(this, "android", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "feature phone", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}