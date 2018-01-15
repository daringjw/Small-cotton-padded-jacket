package com.jinkun.care.network;

import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by coderwjq on 2017/8/24 14:43.
 */

public class FileUploadRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private UploadProgressListener mUploadProgressListener;
    private BufferedSink mBufferedSink;
    private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

    // 将每个requestbody对应一个tag,保证计算的时候不会重复
    private String tag;

    public FileUploadRequestBody(File file, UploadProgressListener uploadProgressListener, String tag) {
        mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        mUploadProgressListener = uploadProgressListener;
        this.tag = tag;
    }

    public FileUploadRequestBody(RequestBody requestBody, UploadProgressListener uploadProgressListener, String tag) {
        mRequestBody = requestBody;
        mUploadProgressListener = uploadProgressListener;
        this.tag = tag;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        // 返回requestbody的类型
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        // 返回requestbody的长度
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (mBufferedSink == null) {
            // 包装
            mBufferedSink = Okio.buffer(sink(sink));
        }

        // 写入
        mRequestBody.writeTo(mBufferedSink);
        // 必须调用,否则最后一部分无法写入
        mBufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            // 当前写入的字节数
            long byteWritten = 0l;
            // 总字节长度,避免多次调用contentLength()
            long contentLength = 0l;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);

                if (contentLength == 0) {
                    // 只调用一次获取总字节数
                    contentLength = contentLength();
                }

                // 增加当前写入的字节数
                byteWritten += byteCount;
                // 回调上传接口
                String progressStr = mDecimalFormat.format((float) byteWritten / contentLength);
                mUploadProgressListener.onProgressChanged(Float.valueOf(progressStr), tag);
            }
        };
    }

    public interface UploadProgressListener {
        void onProgressChanged(float progress, String tag);
    }
}
