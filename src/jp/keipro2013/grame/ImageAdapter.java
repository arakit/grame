package jp.keipro2013.grame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return mThumbIds[position];
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { 
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.stamp0, R.drawable.stamp1,
            R.drawable.stamp2, R.drawable.stamp3,
            R.drawable.stamp4, R.drawable.stamp5,
            //R.drawable.stamp6, 
            R.drawable.stamp7,
            R.drawable.stamp8, R.drawable.stamp9,
            R.drawable.stamp10, R.drawable.stamp11,
            R.drawable.stamp12, R.drawable.stamp13
    };
}