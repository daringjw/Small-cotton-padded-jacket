package com.jinkun.care;

import android.os.Environment;

import java.io.File;

/**
 * @Created by coderwjq on 2017/8/15 17:27.
 * @Desc
 */

public interface Constant {
            String API_HOST = "http://123.56.65.127:8090/yanglao/";
//    String API_HOST = "http://192.168.0.59:8080/yanglao/";
    String PREFIX_FILE_PATH = "File/padFile/";
    String BASE_FILE_PATH = API_HOST + PREFIX_FILE_PATH;
    // pad用户类型的常量
    String PAD_USER_TYPE = "51";

    // 用户信息
    String SP_FILE_NAME_LOCAL_USER_INFO = "sp_file_name_local_user_info";
    String SP_KEY_LOCAL_USER_INFO = "sp_key_local_user_info";
    String SP_KEY_LOCAL_USER_TOKEN = "sp_key_local_user_token";

    // 老人信息录入
    String SP_FILE_NAME_PENDING_FEEDS = "sp_file_name_pending_feeds";
    String SP_KEY_PENDING_FEEDS_DATA = "sp_key_pending_feeds_data";

    boolean DEBUG_MODE = true;
    boolean IS_EMULATOR = true;

    // 默认存放图片的路径
    String DEFAULT_SAVE_AUDIO_PATH = Environment.getExternalStorageDirectory()
            + File.separator
            + "jinkunCare"
            + File.separator + "care_audio_recorder" + File.separator;

    String DEFAULT_SAVE_PHOTO_PATH = Environment.getExternalStorageDirectory()
            + File.separator
            + "jinkunCare"
            + File.separator + "care_images" + File.separator;

    int DEFAULT_ELDER_PHOTO_HEIGHT = 1200;
    int DEFAULT_ELDER_PHOTO_WIDTH = 900;
    int DEFAULT_ELDER_PHOTO_MAX_SIZE = 1024000; // 1M

    // 工单服务商
    String SP_FILE_NAME_ORDER_PROVIDER_INFO = "sp_file_name_order_provider_info";
    String SP_KEY_VILLAGE_INFO = "sp_key_village_info";
    String SP_KEY_TYPE_INFO = "sp_key_type_info";
    String SP_KEY_PROVIDER_INFO = "sp_key_provider_info";
    String SP_KEY_WAITER_INFO = "sp_key_waiter_info";
    String SP_FILE_NAME_PENDING_ORDER = "sp_file_name_pending_order";
    String SP_KEY_PENDING_ORDER = "sp_key_pending_order";
}
