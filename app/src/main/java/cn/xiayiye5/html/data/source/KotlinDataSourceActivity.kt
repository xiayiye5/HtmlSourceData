package cn.xiayiye5.html.data.source

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_html_data_source.*

/**
 * Kotlin版本
 */
class KotlinDataSourceActivity : Activity() {
    /**
     * 下面的HTML注解不能少
     */
    @SuppressLint("JavascriptInterface", "AddJavascriptInterface", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_data_source)
        webViewDataSource.settings.javaScriptEnabled = true
        webViewDataSource.addJavascriptInterface(InJavaScriptLocalObj(), "local_obj")
        webViewDataSource.webViewClient = MyWebViewClient()
        val url = intent.getStringExtra("url")
        webViewDataSource.loadUrl(url!!)
    }

    final class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!);
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d("WebView", "onPageFinished ");
            view?.loadUrl(
                "javascript:window.local_obj.showSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');"
            );
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.d("WebView", "onPageStarted");
            super.onPageStarted(view, url, favicon)
        }
    }

    private var htmlData: String = ""

    //下面必须用内部内的写法，否则 htmlData这个变量在 showSource里面无法获取
    inner class InJavaScriptLocalObj {
        @JavascriptInterface
        fun showSource(html: String?) {
            Log.e("html源码打印", html!!)
            htmlData = html
        }
    }

    fun showNetPageData(view: View) {
        val intent = Intent(this, HtmlDataSourceActivity::class.java)
        if (TextUtils.isEmpty(htmlData)) {
            Toast.makeText(this, "源码数据获取失败,请退出重试！", Toast.LENGTH_SHORT).show()
            return
        }
        intent.putExtra("html_data", htmlData)
        startActivity(intent)
    }
}