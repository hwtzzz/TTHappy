package com.hwt.tthappy.utils;

import cn.hutool.core.lang.UUID;
import com.hwt.tthappy.config.AppConsts;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片工具类
 *
 * @author hu
 * Create by 2024-04-09 17:05
 */
public class ImageUtils {

    /**
     * base64 转 图片
     *
     * @param imgData base64
     * @return 图片名称
     */
    public static String GenerateImage(String imgData) {
        if (imgData == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        String uuid = UUID.randomUUID().toString();
        String filePath = AppConsts.BASE_PATH + uuid + ".jpg";
        try {
            out = new FileOutputStream(filePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return AppConsts.ACCESS_PATH + uuid + ".jpg";
    }

}
