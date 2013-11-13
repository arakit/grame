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

    // Adapter邵ｺ荵晢ｽ芽愾繧会ｿｽ邵ｺ霈費ｽ檎ｹｧ蛹ｺ逵�ｸｺ蜉ｱ�曵mageView郢ｧ蜑�ｽｽ諛茨ｿｽ
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // 郢ｧ�､郢晢ｽｳ郢ｧ�ｹ郢ｧ�ｿ郢晢ｽｳ郢ｧ�ｹ邵ｺ讙主�隰瑚���ｹｧ蠕娯�邵ｺ�ｽ竊醍ｸｺ�ｽ�ｽ陷ｷ蛹ｻ�ｽ闖ｴ諛茨ｿｽ
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

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.stamp0, R.drawable.stamp1,
            R.drawable.stamp2, R.drawable.stamp3,
            R.drawable.stamp4, R.drawable.stamp5,
            //R.drawable.stamp6, R.drawable.stamp7,
            R.drawable.stamp8, R.drawable.stamp9,
            R.drawable.stamp10, R.drawable.stamp11
    };
}