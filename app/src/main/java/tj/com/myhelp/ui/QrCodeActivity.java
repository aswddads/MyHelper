package tj.com.myhelp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.xys.libzxing.zxing.encoding.EncodingUtils;

import tj.com.myhelp.R;

/**
 * Created by Jun on 17/4/30.
 * 生成二维码
 */

public class QrCodeActivity extends BaseActivity {
    private ImageView iv_qr_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        
        initView();
    }

    private void initView() {
        iv_qr_code= (ImageView) findViewById(R.id.iv_qr_code);
        //屏幕的宽
        int width=getResources().getDisplayMetrics().widthPixels;
        Bitmap qrCodeBitmap= EncodingUtils.createQRCode("我是小帮手",width/2,width/2
                , BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        iv_qr_code.setImageBitmap(qrCodeBitmap);
    }
}
