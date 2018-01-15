package com.jinkun.care.helper;

import android.net.Uri;

import com.jinkun.care.Constant;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;

import java.io.File;

/**
 * Created by coderwjq on 2017/8/23 11:20.
 */

public class TakePhotoHelper {
    private TakePhoto mTakePhoto;

    public TakePhotoHelper(TakePhoto takePhoto) {
        mTakePhoto = takePhoto;
        initParams(false);
    }

    public void initParams(boolean isCompress) {
        configCompress(isCompress);
    }

    private void configCompress(boolean isCompress) {
        if (!isCompress) {
            mTakePhoto.onEnableCompress(null, false);
            return;
        }

        // 单位byte
        int maxSize = Constant.DEFAULT_ELDER_PHOTO_MAX_SIZE;
        int width = Constant.DEFAULT_ELDER_PHOTO_WIDTH;
        int height = Constant.DEFAULT_ELDER_PHOTO_HEIGHT;

        // 是否显示压缩进度条
        boolean showProgressBar = true;
        // 拍照压缩后是否保留原图
        boolean enableRawFile = true;
        // 是否使用系统自带压缩
        boolean useSystemCompress = false;

        CompressConfig config;

        if (useSystemCompress) {
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        mTakePhoto.onEnableCompress(config, showProgressBar);
    }

    private CropOptions getCropOptions() {
        int height = Constant.DEFAULT_ELDER_PHOTO_HEIGHT;
        int width = Constant.DEFAULT_ELDER_PHOTO_WIDTH;
        // 使用TakePhoto自带裁剪工具
        boolean withWonCrop = true;

        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setAspectX(3).setAspectY(4);
//        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

    public void takePhoto(File file) {
        mTakePhoto.onPickFromCaptureWithCrop(Uri.fromFile(file), getCropOptions());
    }
}
