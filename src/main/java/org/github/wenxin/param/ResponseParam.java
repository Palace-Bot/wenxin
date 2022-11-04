package org.github.wenxin.param;

import lombok.Data;

/**
 * @author jihongyuan
 * @date 2022/11/3 16:02
 */
@Data
public class ResponseParam {
    private String result;
    private String requestId;
    private String taskId;
}
