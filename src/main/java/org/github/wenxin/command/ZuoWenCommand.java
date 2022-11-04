package org.github.wenxin.command;

import org.apache.commons.lang3.StringUtils;
import org.github.palace.bot.core.annotation.Command;
import org.github.palace.bot.core.annotation.CommandHandler;
import org.github.palace.bot.core.cli.CommandSender;
import org.github.wenxin.PostRequest;
import org.github.wenxin.WenXinCallback;
import org.github.wenxin.WenXinType;
import org.github.wenxin.param.ResponseParam;

/**
 * @author jihongyuan
 * @date 2022/11/2 15:56
 */

@Command(primaryName = "写作文")
public class ZuoWenCommand {

    @CommandHandler
    public void handler(CommandSender commandSender, String[] commands) {
        commandSender.sendMessage("ZZZzzzZZZzzz...");
        PostRequest.request(WenXinType.ZUO_WEN, StringUtils.join(commands, ""), new WenXinCallback<ResponseParam>() {
            @Override
            public void onFailure(String msg, Exception e) {
                commandSender.sendMessage(msg);
            }

            @Override
            public void onSuccess(ResponseParam response) {
                commandSender.sendMessage(response.getResult());
            }
        });
    }
}
