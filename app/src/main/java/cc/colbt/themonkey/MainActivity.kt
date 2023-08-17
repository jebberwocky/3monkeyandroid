package cc.colbt.themonkey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private lateinit var webFragment: WebViewBlankFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webFragment = WebViewBlankFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.thelayout, webFragment)
            .commit()
    }
}