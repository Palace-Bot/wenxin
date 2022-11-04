package org.github.wenxin.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 请求参数
 * <a href="https://wenxin.baidu.com/wenxin/docs#dl6tgxw5f">文心大模型 请求参数</href>
 *
 * @author jihongyuan
 * @date 2022/11/2 15:44
 */
@Data
public class RequestParam {

    /**
     * 模型的输入文本，为prompt形式的输入。
     */
    private String text;

    /**
     * 输出结果的最小长度，避免因模型生成END导致生成长度过短的情况，与seq_len结合使用来设置生成文本的长度范围。
     */
    @JsonProperty("min_dec_len")
    private int minDecLen;

    /**
     * 输出结果的最大长度，因模型生成END或者遇到用户指定的stop_token，实际返回结果可能会小于这个长度，与min_dec_len结合使用来控制生成文本的长度范围。
     */
    @JsonProperty("seq_len")
    private int seqLen;

    /**
     * 影响输出文本的多样性，取值越大，生成文本的多样性越强。
     */
    private float topp;

    /**
     * 通过对已生成的token增加惩罚，减少重复生成的现象。值越大表示惩罚越大。设置过大会导致长文本生成效果变差。
     */
    @JsonProperty("penalty_score")
    private float penaltyScore;

    /**
     * 预测结果解析时使用的结束字符串，碰到对应字符串则直接截断并返回。可以通过设置该值，可以过滤掉few-shot等场景下模型重复的cases。
     */
    @JsonProperty("stop_token")
    private String stopToken;

    @JsonProperty("task_prompt")
    private String taskPrompt;

    // 画画参数

    private String style;

    private String resolution;

    // TODO 还有一些不重要

    public RequestParam() {
        this.minDecLen = 10;
        this.seqLen = 400;
        this.topp = 1.0f;
        this.penaltyScore = 1.0f;
        this.stopToken = "";
    }

}