package org.github.wenxin;

import org.github.palace.bot.core.annotation.Application;
import org.github.palace.bot.core.annotation.OnLoad;
import org.github.palace.bot.core.plugin.Plugin;

/**
 * @author jihongyuan
 * @date 2022/11/2 15:17
 */
@Application(name = "文心", version = "0.0.1", scanBasePackages = "classpath*:org/github/wenxin/**/**.class")
public class App extends Plugin {

    @OnLoad
    public void onLoad() {
    }

}