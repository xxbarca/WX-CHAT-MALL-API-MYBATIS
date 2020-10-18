package com.ly.imallbatis.dto;

import com.ly.imallbatis.util.HttpRequestProxy;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDTO {

    private Integer code = 0;
    private String message = "OK";
    private String request = HttpRequestProxy.getRequestUrl();
}
