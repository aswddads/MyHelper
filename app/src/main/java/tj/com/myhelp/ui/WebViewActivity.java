package tj.com.myhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;

import tj.com.myhelp.R;

/**
 * Created by Jun on 17/4/30.
 */

public class WebViewActivity extends BaseActivity{
    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
    }

    private void initView() {
        mProgressBar=(ProgressBar)findViewById(R.id.mProgressBar);
        mWebView=(WebView)findViewById(R.id.mWebView);

        //拿到传过来的值
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        final String url=intent.getStringExtra("url");
        //设置标题
        getSupportActionBar().setTitle(title);
//        加载网页
//        支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
//        支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //本地接口
        mWebView.setWebChromeClient(new WebViewClient());
//        加载网页
        mWebView.loadUrl(url);
//        本地显示加载
        mWebView.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
//                返回true，标示接受此事件
                return true;
            }
        });
    }
    public class WebViewClient extends WebChromeClient{
//        进度变化监听
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress==100){
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
