package com.ms.wms.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.type.ArrayType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Routine> routineList = new ArrayList<>();

    public Member(String oauthId, String name, String providerName, String accessToken, String refreshToken) {

        this.oauthId = oauthId;
        this.name = name;
        this.providerName = providerName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}