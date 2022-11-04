package org.github.wenxin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.github.palace.bot.utils.JSONUtil;

/**
 * @author jihongyuan
 * @date 2022/11/2 16:00
 */
@Slf4j
public class AccessToken {

    @JsonProperty("grant_type")
    private  String grantType = "client_credentials";

    @JsonProperty("client_id")
    private  String clientId = "";

    @JsonProperty("client_secret")
    private  String clientSecret = "";

    @JsonProperty("grant_type")
    public String getGrantType() {
        return grantType;
    }

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static String accessToken;

    public static synchronized String get() {
        if (accessToken == null) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSONUtil.toJsonString(new AccessToken()), JSON);
            Request request = new Request.Builder()
                    .url("https://wenxin.baidu.com/moduleApi/portal/api/oauth/token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                accessToken = ResponseUtil.parseData(response.body().string());
                return accessToken;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return accessToken;
    }

    public static void main(String[] args) {

    }


}
