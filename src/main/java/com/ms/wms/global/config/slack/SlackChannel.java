package com.ms.wms.global.config.slack;

import lombok.Getter;

@Getter
public enum SlackChannel {

    CHANNEL_WMS("https://hooks.slack.com/services/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"),
    CHANNEL_MEMBER("https://hooks.slack.com/services/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");

    String url;

    SlackChannel(String url) {
        this.url = url;
    }
}
