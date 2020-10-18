package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.dto.SuccessDTO;
import com.ly.imallbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/wx_info")
    @ScopeLevel
    public SuccessDTO updateUserInfo(@RequestBody Map<String, Object> user) {
        userService.updateUserWxInfo(user);
        return new SuccessDTO();
    }

}
