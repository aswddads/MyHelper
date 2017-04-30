package tj.com.myhelp.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import tj.com.myhelp.R;
import tj.com.myhelp.service.SmsService;
import tj.com.myhelp.utils.SpUtils;

/**
 * Created by Jun on 17/4/28.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    //语音播报
    private Switch sw_speak;
//    短信提醒
    private Switch sw_sms;
//    检测新版本
    private LinearLayout ll_update;
    private TextView tv_versioon;
    private String versionName;
    private int versionCode;

//    扫一扫
    private LinearLayout ll_scan;
//    扫描的结果
    private TextView tv_scan_result;

    //生成二维码
    private LinearLayout ll_qr_code;

    private LinearLayout ll_my_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();

    }

    private void initView() {
        sw_speak=(Switch)findViewById(R.id.sw_speak);
        sw_speak.setOnClickListener(this);
        sw_sms=(Switch)findViewById(R.id.sw_sms);
        sw_sms.setOnClickListener(this);

        boolean isSpeak=SpUtils.getBoolean(this,"isSpeak",false);
        sw_speak.setChecked(isSpeak);
        boolean isSms=SpUtils.getBoolean(this,"isSms",false);
        sw_sms.setChecked(isSms);

        ll_update=(LinearLayout)findViewById(R.id.ll_update);
        ll_update.setOnClickListener(this);
        tv_versioon=(TextView)findViewById(R.id.tv_version);

        getVersionNameCode();
        tv_versioon.setText("检测版本"+versionName);

        ll_scan= (LinearLayout) findViewById(R.id.ll_scan);
        ll_scan.setOnClickListener(this);

        tv_scan_result=(TextView)findViewById(R.id.tv_scan_result);
        ll_qr_code= (LinearLayout) findViewById(R.id.ll_qr_code);

        ll_my_location= (LinearLayout) findViewById(R.id.ll_my_location);
        ll_my_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sw_speak:
                //切换状态
                sw_speak.setSelected(!sw_speak.isSelected());
                //保存状态
                SpUtils.putBoolean(this,"isSpeak",sw_speak.isChecked());
                break;
            case R.id.sw_sms:
                //切换相反
                sw_sms.setSelected(!sw_sms.isSelected());
                SpUtils.putBoolean(this,"isSms",sw_sms.isChecked());
                if (sw_sms.isChecked()){
                    startService(new Intent(this,SmsService.class));
                }else {
                    stopService(new Intent(this,SmsService.class));
                }
                break;
            case R.id.ll_update:
                /**
                 * 1.请求服务器配置文件，拿到code
                 * 2.比较 3.diaolog 4.跳转更新版本，传递url
                 */
                Toast.makeText(this,"逗你尼！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_scan:
//                打开扫描二维码界面
                Intent openCameraIntent=new Intent(this,CaptureActivity.class);
                startActivityForResult(openCameraIntent,0);
                break;
            case R.id.ll_qr_code:
                startActivity(new Intent(this,QrCodeActivity.class));
                break;
            case R.id.ll_my_location:
                //startActivity(new Intent(this,LocationActivity.class));
                Toast.makeText(this,"bug了",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 获取版本号
     */
    private void getVersionNameCode(){
        PackageManager pm=getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(getPackageName(),0);
            versionName=info.versionName;
            versionCode=info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String scanResult=bundle.getString("result");
            tv_scan_result.setText(scanResult);
        }
    }
}
