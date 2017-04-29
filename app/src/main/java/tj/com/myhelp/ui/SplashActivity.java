package tj.com.myhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tj.com.myhelp.R;
import tj.com.myhelp.utils.SpUtils;
import tj.com.myhelp.utils.StaticClass;
import tj.com.myhelp.utils.UtilsTools;

/**
 * Created by Jun on 17/4/28.
 * 闪屏页面
 */

public class SplashActivity extends AppCompatActivity {
    private TextView tv_splash;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
//                    判读是否第一次进入
                    if (isFirst()) {
                        startActivity(new Intent(getApplicationContext(),GuideActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    /**
     * 延时2000ms，判断程序是否第一次运行，自定义字体，全屏
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    //  初始化view
    private void initView() {
        //延时2秒
        mHandler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
        tv_splash = (TextView) findViewById(R.id.tv_splash);
//       设置字体
        UtilsTools.setFont(this,tv_splash);
    }

    /**
     * 判断程序是否第一次执行
     */
    private boolean isFirst() {
        boolean isFirst = SpUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst){
            SpUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            return true;
        }else {
            return false;
        }
    }


    //禁止返回键
}
