package tj.com.myhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import tj.com.myhelp.R;
import tj.com.myhelp.entity.GirlData;
import tj.com.myhelp.utils.PicassoUtils;

/**
 * Created by Jun on 17/4/30.
 */

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<GirlData> mList;
    private LayoutInflater inflater;
    private GirlData data;
    private WindowManager wm;
    private int width;

    public GridAdapter(Context mContext,List<GirlData> mList) {
        this.mContext=mContext;
        this.mList=mList;
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width=wm.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.girl_item,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        data=mList.get(position);
        //解析图片
        String url=data.getImgUrl();

        PicassoUtils.loadImageViewSize(mContext,url,width/2,250,viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
    }
}
