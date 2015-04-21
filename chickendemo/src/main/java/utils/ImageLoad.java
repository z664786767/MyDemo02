package utils;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

import org.mobiletrain.www.chickendemo.R;

/**
 * Created by aaa on 15-4-16.
 */
public class ImageLoad {

    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils(Context context){
        if(bitmapUtils!=null){
            return bitmapUtils;
        }
        bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
        bitmapUtils.configMemoryCacheEnabled(true);
        bitmapUtils.configDiskCacheEnabled(true);

        return bitmapUtils;
    }
}
