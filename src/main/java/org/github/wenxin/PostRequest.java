package org.github.wenxin;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.github.palace.bot.utils.JSONUtil;
import org.github.wenxin.param.RequestParam;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author jihongyuan
 * @date 2022/11/2 16:42
 */
public class PostRequest {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void request(WenXinType wenXinType, String text, WenXinCallback<?> callback) {
        RequestParam requestParam = new RequestParam();
        requestParam.setText(MessageFormat.format(wenXinType.template, text));
        requestParam.setTaskPrompt(wenXinType.type);
        request(wenXinType, requestParam, callback);
    }

    public static void request(WenXinType wenXinType, RequestParam requestParam, WenXinCallback<?> callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(360, TimeUnit.SECONDS)
                .readTimeout(360, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(JSONUtil.toJsonString(requestParam), JSON);
        Request request = new Request.Builder()
                .url(wenXinType.url + "?access_token=" + AccessToken.get())
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
