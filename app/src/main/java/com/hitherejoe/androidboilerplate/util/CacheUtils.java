package com.hitherejoe.androidboilerplate.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import timber.log.Timber;


/**
 * Created by Srihari S Reddy on 18/11/16.
 */

public class CacheUtils {
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File cacheFile=context.getExternalCacheDir();
            if(cacheFile!=null&&!cacheFile.exists()){
                cacheFile.mkdirs();
            }
            Timber.i("Cache File @ "+cacheFile.getAbsolutePath());
            cachePath = cacheFile.getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        File fileDir=new File(cachePath + File.separator + uniqueName);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
