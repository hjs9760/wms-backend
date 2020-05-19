package com.ms.wms.global.config.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SlackMessage {

    public void sendSlackMessage(SlackChannel slackChannel, String msg) {
        Payload payload = Payload.builder()
                .text(msg)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(slackChannel.getUrl(), payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
