package tj.com.myhelp.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;
import tj.com.myhelp.utils.StaticClass;

/**
 * Created by Jun on 17/4/28.
 */

public class MyApplication extends Application {
//    创建
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
//        初始化Bmob
        //第一：默认初始化
        Bmob.initialize(this,StaticClass.BMOB_APP_ID);
    }
}
