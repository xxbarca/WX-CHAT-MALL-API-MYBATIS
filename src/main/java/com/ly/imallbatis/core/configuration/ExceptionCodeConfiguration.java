package com.ly.imallbatis.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/***
 *
 * 类和配置文件相对应, codes 和 配置文件中的 codes 自动匹配
 *
 */

@ConfigurationProperties(prefix = "mall")
@PropertySource(value = "classpath:config/exception-code.properties")
@Component
public class ExceptionCodeConfiguration {

    private Map<Integer, String> codes = new HashMap<>();

    public String getMessage(int code) throws UnsupportedEncodingException {
        String message = codes.get(code);
        message = URLEncoder.encode(message, "ISO-8859-1");
        message = URLDecoder.decode(message, "UTF-8");
        System.out.println(message);
        return message;
    }

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }
}
