package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.dto.TokenGetDTO;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData) {

        Map<String, String> map = new HashMap<>();
        String token = null;
        switch (userData.getType()) {
            case USER_EMAIL:
                token = authenticationService.code2SessionByEmail(userData.getAccount());
                break;
            case USER_WX:
                token = authenticationService.code2Session(userData.getAccount());
                break;
            default:
                throw new NotFoundException(10003);
        }
        map.put("token", token);
        return map;
    }
}
