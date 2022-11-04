package org.github.wenxin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.github.palace.bot.utils.JSONUtil;

/**
 * @author jihongyuan
 * @date 2022/11/3 15:53
 */
public class ResponseUtil {

    public static String parseData(String body) {
        JsonNode rootNode;
        try {
            rootNode = JSONUtil.OBJECT_MAPPER.readTree(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        int code = rootNode.get("code").asInt();
        if (code != 0) {
            throw new RuntimeException(rootNode.get("msg").asText());
        }

        return rootNode.get("data").toString();
    }

}
