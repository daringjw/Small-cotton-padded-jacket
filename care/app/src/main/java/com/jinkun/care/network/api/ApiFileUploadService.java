package com.jinkun.care.network.api;

import com.jinkun.care.model.response.FileUploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/8/24 9:07.
 */

public interface ApiFileUploadService {
    @Multipart
    @POST("pad/uploadFile.do")
    Call<FileUploadResponse> uploadImage(@Query("token") String token,
                                         @Part MultipartBody.Part image);

}
