package com.yangchedou.lib_common.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

/**
 * Created by ADMIN on 2017/11/27.
 */

public class TakePhotoUtil {

    public static TakePhotoUtil intance = null;

    private Context context;

    private CropOptions cropOptions = null;

    private CompressConfig compressConfig = null;

    public static TakePhotoUtil getIntance(){
        if (intance==null){
            synchronized (TakePhoto.class){
                intance = new TakePhotoUtil();
            }
        }
        return intance;
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    public void initData(TakePhoto takePhoto) {
        //设置裁剪参数
        if (cropOptions==null){
            getCropOptions();
        }
        //设置压缩参数
        if (compressConfig==null){
            getCompressConfig();
        }
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }

    public CropOptions getCropOptions(){
        if (cropOptions==null){
            cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        }
        return cropOptions;
    }

    public CompressConfig getCompressConfig(){
        if (compressConfig==null){
            compressConfig = new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        }
        return compressConfig;
    }


}
