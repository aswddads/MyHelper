package tj.com.myhelp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Jun on 17/4/28.
 * 工具统一类
 */

/**
 * 设置字体
 */
public class UtilsTools {
    public static void setFont(Context context, TextView textView){
        Typeface fontType=Typeface.createFromAsset(context.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }

    /**
     * 保存图片到sp
     * @param context
     * @param imageView
     */
    public static void putImageToSp(Context context,ImageView imageView){
        //保存
        BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        //将bitmap压缩至字节数组输出流
        ByteArrayOutputStream byStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,byStream);
        //利用base64将字节数组输出流转换成string
        byte[] byteArray=byStream.toByteArray();
        String imgString=new String(Base64.encodeToString(byteArray,Base64.DEFAULT));
        //将string保存在sp中
        SpUtils.putString(context,"image_title",imgString);
    }

    /**
     * 读取图片
     * @param context
     * @param imageView
     */
    public static void getImageFromSp(Context context,ImageView imageView){
        //拿到string
        String imgString=SpUtils.getString(context,"image_title","");
        if (!imgString.equals("")){
            //利用base64把string转换
            byte[] byteArray=Base64.decode(imgString,Base64.DEFAULT);
            ByteArrayInputStream byteStream=new ByteArrayInputStream(byteArray);
            //生成bitmap
            Bitmap bitmap= BitmapFactory.decodeStream(byteStream);
            imageView.setImageBitmap(bitmap);
        }

    }

}
