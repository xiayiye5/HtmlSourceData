package cn.xiayiye5.html.data.source

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var urlData: String = "https://blog.csdn.net/xiayiye5"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun java(view: View) {
        urlData = etUrlData.text.toString().trim()
        val intent = Intent(this, JavaDataSourceActivity::class.java)
        if (!urlData.startsWith("http")) {
            Toast.makeText(this, "网址输入有误,请输入http开头的网址", Toast.LENGTH_SHORT).show()
            return
        }
        intent.putExtra("url", urlData);
        startActivity(intent)
    }

    fun kotlin(view: View) {
        urlData = etUrlData.text.toString().trim()
        val intent = Intent(this, KotlinDataSourceActivity::class.java)
        intent.putExtra("url", urlData);
        startActivity(intent)
    }
}