package org.github.wenxin;

/**
 * @author jihongyuan
 * @date 2022/11/4 10:37
 */
public enum WenXinType {

    ZUO_WEN("zuowen", "https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernie/3.0.21/zeus", "作文题目：{0}"),

    COUPLET("couplet", "https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernie/3.0.24/zeus", "上联：{0}\n下联："),

    NOVEL("novel", "https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernie/3.0.26/zeus", "{0}"),

    DRAW("","https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernievilg/v1/txt2img","{0}")
    ;

    public final String type;

    public final String url;
    public final String template;

    WenXinType(String type, String url, String template) {
        this.type = type;
        this.url = url;
        this.template = template;
    }

}
