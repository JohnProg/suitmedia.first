package dev.alfianh.firsttestsuitmedia.activities

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import dev.alfianh.firsttestsuitmedia.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNext.setOnClickListener {
            if (etName.text.isEmpty())
                etName.error = "Harus diisi !"
            else {
                var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                dialog.setMessage(isPalindrome(etName.text.toString()))
                dialog.setPositiveButton("Next", DialogInterface.OnClickListener { dialog, which -> MenuActivity.start(this, etName.text.toString()) })
                dialog.show()
            }
        }
    }

    fun isPalindrome(s: String): String {
        val n = s.length
        for (i in 0..n / 2 - 1) {
            if (s[i] != s[n - i - 1]) {
                return "not palindrome"
            }
        }
        return "isPalindrome"
    }
}
