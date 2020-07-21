package com.ly.imallbatis.dto;

import com.ly.imallbatis.core.Enumeration.LoginType;
import com.ly.imallbatis.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TokenGetDTO {

    @NotBlank(message = "account不允许为空")
    private String account;

    @TokenPassword(max = 30, message = "{token.password}")
    private String password;

    private LoginType type;
}
