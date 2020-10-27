package cn.xiayiye5.html.data.source;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author xiayiye5
 * 2020年10月26日09:49:48
 */
public class JavaDataSourceActivity extends AppCompatActivity {
   static HtmlDataBean htmlDataBean = new HtmlDataBean();
    /**
     * 下面的这个注解一定要添加
     *
     * @param savedInstanceState 注解
     */
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_data_source);
        WebView webViewDataSource = findViewById(R.id.webViewDataSource);
        webViewDataSource.getSettings().setJavaScriptEnabled(true);
        webViewDataSource.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webViewDataSource.setWebViewClient(new MyWebViewClient());
        String url = getIntent().getStringExtra("url");
        assert url != null;
        webViewDataSource.loadUrl(url);
    }

    final class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d("WebView", "onPageStarted");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("WebView", "onPageFinished ");
            view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            super.onPageFinished(view, url);
        }
    }

    public static String htmlData;

    static final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            Log.e("html源码打印", html);
            htmlDataBean.setSourceData(html);
            htmlData = html;
        }
    }

    public void showNetPageData(View view) {
        Intent intent = new Intent(this, HtmlDataSourceActivity.class);
        if (TextUtils.isEmpty(htmlData)) {
            Toast.makeText(this, "源码数据获取失败,请退出重试！", Toast.LENGTH_SHORT).show();
            return;
        }
        //方法一
//        intent.putExtra("html_page", 1);
        //方法二
        intent.putExtra("html_data", htmlDataBean);
        startActivity(intent);
    }
}
