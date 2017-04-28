package tj.com.myhelp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

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
}
