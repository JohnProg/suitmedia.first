package dev.alfianh.firsttestsuitmedia.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dev.alfianh.firsttestsuitmedia.R
import kotlinx.android.synthetic.main.activity_event.*
import dev.alfianh.firsttestsuitmedia.fragments.EventFragment
import dev.alfianh.firsttestsuitmedia.fragments.MapFragment


/**
 * Created by alfianh on 8/9/17.
 */
class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        initToolBar()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.eventContent, EventFragment()).commit()
    }

    companion object {
        fun start(context: Context) {
            (context as Activity).startActivityForResult(Intent(context, EventActivity::class.java), MenuActivity.ACTIVITY_REQUEST_CODE)
        }
    }

    fun initToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.btn_back_article_selected)
        toolbar.setNavigationOnClickListener { v -> finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_new_media -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.eventContent, MapFragment()).commit()
                return true
            }
        }
        return false
    }
}