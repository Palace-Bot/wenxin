package org.github.wenxin.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.github.palace.bot.core.annotation.Command;
import org.github.palace.bot.core.annotation.CommandHandler;
import org.github.palace.bot.core.cli.CommandSender;
import org.github.palace.bot.utils.JSONUtil;
import org.github.wenxin.*;
import org.github.wenxin.param.RequestParam;
import org.github.wenxin.param.ResponseParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jihongyuan
 * @date 2022/11/4 11:00
 */
@Slf4j
@Command(primaryName = "画画")
public class DrawCommand {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @CommandHandler
    public void handler(CommandSender commandSender, String[] commands) {

        RequestParam requestParam = new RequestParam();
        requestParam.setText(commands[0]);
        requestParam.setStyle(commands[1]);
        requestParam.setResolution(commands[2]);

        PostRequest.request(WenXinType.DRAW, requestParam, new WenXinCallback<ResponseParam>() {
            @Override
            public void onFailure(String msg, Exception e) {
                commandSender.sendMessage(msg);
            }

            @Override
            public void onSuccess(ResponseParam responseParam) {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                }

                OkHttpClient client = new OkHttpClient();
                Map<String, String> tokenMap = new HashMap<>(2);
                tokenMap.put("access_token", AccessToken.get());
                tokenMap.put("taskId", responseParam.getTaskId());

                RequestBody body = RequestBody.create(JSONUtil.toJsonString(tokenMap), JSON);
                Request request = new Request.Builder()
                        .url("https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernievilg/v1/getImg")
                        .post(body)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    String data = ResponseUtil.parseData(response.body().string());
                    JsonNode rootNode;
                    try {
                        rootNode = JSONUtil.OBJECT_MAPPER.readTree(data);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    List<String> valuesAsText = rootNode.get("imgUrls").findValuesAsText("image");
                    for (String s : valuesAsText) {
                        URL url = new URL(s);
                        try(InputStream is = url.openStream()){
                            Image image = commandSender.getSubject().uploadImage(ExternalResource.create(is));
                            commandSender.sendMessage(image);
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });
    }

}
