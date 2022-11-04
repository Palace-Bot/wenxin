package org.github.wenxin;

import okhttp3.*;
import org.github.palace.bot.utils.JSONUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * @author jihongyuan
 * @date 2022/11/2 16:43
 */
@FunctionalInterface
public interface WenXinCallback<T> extends Callback {

    void onSuccess(T response);

    default void onFailure(@NotNull Call call, @NotNull IOException e) {
        System.out.println(call.toString());
    }

    default void onFailure(String msg, Exception e) {
    }

    @SuppressWarnings("unchecked")
    default void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try (ResponseBody responseBody = response.body()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericInterfaces()[0];
            Class<?> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            String data = null;
            try {
                data = ResponseUtil.parseData(response.body().string());
            } catch (Exception e) {
                onFailure(e.getMessage(), e);
            }
            if (clazz == String.class) {
                onSuccess((T) data);
            } else {
                onSuccess(JSONUtil.readValue(data, (Class<T>) parameterizedType.getActualTypeArguments()[0]));
            }
        }
    }

}
