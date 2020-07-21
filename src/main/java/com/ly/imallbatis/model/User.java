package com.ly.imallbatis.model;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private Long id;
    private String openid;

    private String nickname;

    private String email;

    private String mobile;

    private String password;

    private Long unifyUid;

    private String group;

    private Map<String, Object> wxProfile;
}
