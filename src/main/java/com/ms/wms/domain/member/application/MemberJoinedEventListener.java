package com.ms.wms.domain.member.application;

import com.ms.wms.global.config.slack.SlackChannel;
import com.ms.wms.global.config.slack.SlackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberJoinedEventListener {

    private final SlackMessage slackMessage;

    @EventListener
    public void handle(MemberJoinedEvent memberJoinedEvent) {
        String memberName = memberJoinedEvent.getMember().getName();
        slackMessage.sendSlackMessage(SlackChannel.CHANNEL_MEMBER, memberName + " 님이 wms에 회원이 되었습니다.");
    }
}
