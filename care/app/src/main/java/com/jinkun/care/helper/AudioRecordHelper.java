package com.jinkun.care.helper;

import android.app.Activity;
import android.media.MediaRecorder;

import com.jinkun.care.Constant;

import java.io.IOException;

/**
 * Created by coderwjq on 2017/8/22 15:07.
 */

public class AudioRecordHelper {
    private static AudioRecordHelper mInstance;
    private MediaRecorder mMediaRecorder;
    private boolean hasInitialized = false;
    private boolean isRecording = false;
    private Activity mContext;

    private AudioRecordHelper(Activity context) {
        mContext = context;
    }

    public static AudioRecordHelper getInstance(Activity context) {
        if (mInstance == null) {
            synchronized (AudioRecordHelper.class) {
                if (mInstance == null) {
                    mInstance = new AudioRecordHelper(context);
                }
            }
        }
        return mInstance;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void init(String destFilePath) {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.DEFAULT);
        mMediaRecorder.setOutputFile(destFilePath);

        hasInitialized = true;
    }

    public void start() {
        if (!hasInitialized) {
            throw new RuntimeException("请先进行初始化");
        }

        if (!Constant.IS_EMULATOR) {
            try {
                mMediaRecorder.prepare();
                mMediaRecorder.start();
                isRecording = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (!Constant.IS_EMULATOR) {
            mMediaRecorder.stop();
            mMediaRecorder.release();
        }
        mMediaRecorder = null;
        isRecording = false;
    }
}
