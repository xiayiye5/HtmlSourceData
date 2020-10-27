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
        //数据传递方法一
        /* val htmlPage = intent.getIntExtra("html_page", 0)
         if (htmlPage == 1) {
             tvShowData.text = JavaDataSourceActivity.htmlData
         } else if (htmlPage == 2) {
             tvShowData.text = KotlinDataSourceActivity.htmlData
         }*/
        //数据传递方法二,通过bean对象
        val htmlDataBean = intent.getSerializableExtra("html_data") as HtmlDataBean
        tvShowData.text = htmlDataBean.sourceData
    }
}