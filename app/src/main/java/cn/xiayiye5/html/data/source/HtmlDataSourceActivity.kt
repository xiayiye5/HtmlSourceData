package cn.xiayiye5.html.data.source

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_html_data_source_page.*

/**
 * @author xiayiye5
 * 2020年10月26日09:58:08
 * 获取HTML源码展示的页面
 */
class HtmlDataSourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_data_source_page)
        val htmlData = intent.getStringExtra("html_data")
        tvShowData.text = htmlData
    }
}