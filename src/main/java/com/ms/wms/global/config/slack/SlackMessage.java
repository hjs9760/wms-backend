package com.ms.wms.global.config.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SlackMessage {

    @Value("${slack.webhook}")
    private String urlSlackWebHook;

    public void sendSlackMessage(String result, String msg) {
        Payload payload = Payload.builder()
                .username(result + "Message")
                .text(msg)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
