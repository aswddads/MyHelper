package tj.com.myhelp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Jun on 17/4/30.
 * Picass的封装
 */

public class PicassoUtils {
    //默认加载模式
    public static void loadImageView(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }

    //    加载指定大小的图片
    public static void loadImageViewSize(Context context, String url, int width, int height, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .resize(width, height)
                .centerCrop()
                .into(imageView);
    }

    //加载图片有默认图片
    public static void loadImageViewHolder(Context context, String url, int loadImg, int errorImg, ImageView imageView) {
        Picasso.with(context).load(url)
                .placeholder(loadImg)
                .error(errorImg)
                .into(imageView);
    }
    //加载裁剪图片
    public static void loadImageViewCrop(Context context, String url, ImageView imageView){
        Picasso.with(context).load(url).transform(new CropSquareTransformation()).into(imageView);

    }
    //按比例裁剪 矩形
    public static class CropSquareTransformation implements Transformation{

        @Override
        public Bitmap transform(Bitmap source) {
            int size=Math.min(source.getWidth(),source.getHeight());
            int x=(source.getWidth()-size)/2;
            int y=(source.getHeight()-size)/2;
            Bitmap result=Bitmap.createBitmap(source,x,y,size,size);
            if (result!=source){
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "tj";
        }
    }
}
