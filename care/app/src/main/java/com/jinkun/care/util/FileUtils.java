package com.jinkun.care.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.jinkun.care.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by coderwjq on 16/1/6.
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    private static final int RANDOM_STRING_LENGTH = 6;
    private static final String TEMP_IMAGE_DIR = "/jinkunCare/care_images/";

    public static String generateFileName() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(new Date());
        return formatDate + RandomStringUtils.getRandomString(RANDOM_STRING_LENGTH);
    }

    public static String generateTmpPhotoFilePath(Context context) {
        String state = Environment.getExternalStorageState();
        File fileDir;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            fileDir = Environment.getExternalStorageDirectory();
        } else {
            fileDir = context.getFilesDir();
        }
        return fileDir.getAbsolutePath() + TEMP_IMAGE_DIR + generateFileName() + ".jpg";
    }

    public static File createTmpPhotoFile(Context context) {
        String state = Environment.getExternalStorageState();
        File fileDir;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            fileDir = Environment.getExternalStorageDirectory();
        } else {
            fileDir = context.getFilesDir();
        }
        String imagePath = fileDir.getAbsolutePath() + TEMP_IMAGE_DIR;
        File imageDir = new File(imagePath);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        return new File(imageDir, generateFileName() + ".jpg");
    }

    public static File createPhotoFile(Context context) {
        String state = Environment.getExternalStorageState();
        File fileDir;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            fileDir = Environment.getExternalStorageDirectory();
        } else {
            fileDir = context.getFilesDir();
        }
        String imagePath = fileDir.getAbsolutePath() + "/Picture";
        File imageDir = new File(imagePath);
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        return new File(imageDir, generateFileName() + ".jpg");
    }

    public static boolean saveBitmap2File(Bitmap bitmap, String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String convertStreamToString(InputStream is)
            throws IOException {
        Writer writer = new StringWriter();

        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        return writer.toString();
    }

    public static Bitmap getStickerBitmapFromAsset(Context context, String strName) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(istr, null, options);

            int TARGET_HEIGHT, TARGET_WIDTH;

            TARGET_HEIGHT = TARGET_WIDTH = context
                    .getResources()
                    .getDimensionPixelOffset(R.dimen.max_sticker_require_size);

            Boolean scaleByHeight = Math.abs(options.outHeight - TARGET_HEIGHT) >= Math.abs(
                    options.outWidth - TARGET_WIDTH);

            if (options.outHeight * options.outWidth * 2 >= 200 * 200 * 2) {
                double sampleSize = scaleByHeight
                        ? options.outHeight / TARGET_HEIGHT
                        : options.outWidth / TARGET_WIDTH;
                options.inSampleSize =
                        (int) Math.pow(2d, Math.floor(
                                Math.log(sampleSize) / Math.log(2d)));
            }

            // Do the actual decoding
            options.inJustDecodeBounds = false;

            istr.close();
            istr = assetManager.open(strName);
            Bitmap bitmap = BitmapFactory.decodeStream(istr, null, options);
            istr.close();
            return bitmap;
        } catch (IOException e) {
            Log.e(TAG, "load image sticker failed", e);
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "load image sticker OOM", e);
        }
        return null;
    }

    public static boolean isFileExists(String path) {
        return new File(path).exists();
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     * @param requestCode result requestCode
     */
    public static void startActionCapture(Activity activity, File file, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, file));
        activity.startActivityForResult(intent, requestCode);
    }


    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.honjane.providerdemo.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
}