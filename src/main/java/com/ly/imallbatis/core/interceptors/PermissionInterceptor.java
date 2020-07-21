package com.ly.imallbatis.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.ly.imallbatis.core.LocalUser;
import com.ly.imallbatis.exception.http.ForbiddenException;
import com.ly.imallbatis.exception.http.UnAuthenticatedException;
import com.ly.imallbatis.model.User;
import com.ly.imallbatis.service.UserService;
import com.ly.imallbatis.util.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    public PermissionInterceptor() {
        super();
    }

    /**
     * 1. 获取到请求token
     * 2. 验证token
     * 3. scope
     * 4. 读取API @ScopeLevel level值
     * 5. 比较 scope 与 level
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1. 读取@ScopeLevel里面的value值
         * */
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            // @ScopeLevel 注解不存在 说明是公开的方法
            return true;
        }
        String bearerToken = this.getToken(request);
        if (StringUtils.isEmpty(bearerToken)) {
            throw new UnAuthenticatedException(10004);
        }

        if (!bearerToken.startsWith("Bearer")) {
            throw new UnAuthenticatedException(10004);
        }

        //
        String tokens[] = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new UnAuthenticatedException(10004);
        }

        // Authorization: Bearer <token>
        String token = tokens[1];

        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);

        Map<String, Claim> map = optionalMap.orElseThrow(() -> new UnAuthenticatedException(10004));

        // 比对 令牌与scopeLevel
        boolean valid = this.hasPermission(scopeLevel.get(), map);
        if (valid) {
            this.setToThreadLocal(map);
        }
        return valid;
    }

    /**
     * 保存当前用户
     * */
    private void setToThreadLocal(Map<String, Claim> map) {
        Long uid = map.get("uid").asLong();
        Integer scope = map.get("scope").asInt();
        User user = userService.getUserById(uid);
        LocalUser.set(user, scope);
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        Integer level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalUser.clear(); /** 释放当前线程资源 */
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    /**
     * 获取前端传递的token
     * */
    private String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
