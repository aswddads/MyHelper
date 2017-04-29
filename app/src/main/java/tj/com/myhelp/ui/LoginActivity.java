package tj.com.myhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import tj.com.myhelp.MainActivity;
import tj.com.myhelp.R;
import tj.com.myhelp.entity.MyUser;
import tj.com.myhelp.utils.SpUtils;
import tj.com.myhelp.view.CustomDialog;

/**
 * Created by Jun on 17/4/29.
 * 登陆
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //    注册按钮
    private Button btn_registered;

    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;

    private CheckBox keep_password;
    private TextView tv_forget;

    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        //点击其他地方无效
        dialog.setCancelable(false);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
//        设置选中的状态
        boolean isCheck = SpUtils.getBoolean(this, "keeppass", false);
        keep_password.setChecked(isCheck);
        if (isCheck) {
            et_name.setText(SpUtils.getString(this, "name", ""));
            et_password.setText(SpUtils.getString(this, "password", ""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btn_login:
//                获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
//                判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    dialog.show();
                    //进行登陆
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            //判断结果
                            if (e == null) {
                                //判断邮箱是否验证
                                if (user.getEmailVerified()) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登陆失败" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        保存状态
        SpUtils.putBoolean(this, "keeppass", keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            SpUtils.putString(this, "name", et_name.getText().toString().trim());
            SpUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {//清除密码
            SpUtils.deletShare(this, "name");
            SpUtils.deletShare(this, "password");
        }
    }
}
