package com.ms.wms.domain.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Setter
    private Long id;

    @Column
    private String oauthId;

    @Column
    private String name;

    @Column
    private String providerName;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    public Member(String oauthId, String name, String providerName, String accessToken, String refreshToken) {

        this.oauthId = oauthId;
        this.name = name;
        this.providerName = providerName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}