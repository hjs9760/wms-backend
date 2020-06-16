package com.ms.wms.global.config.exception;

import com.ms.wms.global.config.slack.SlackChannel;
import com.ms.wms.global.config.slack.SlackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExceptionEventListener {

    private final SlackMessage slackMessage;

    @EventListener
    public void handle(Exception e) {
        slackMessage.sendSlackMessage(SlackChannel.CHANNEL_WMS, e.getMessage());
    }
}
