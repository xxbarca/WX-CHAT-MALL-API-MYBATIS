package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.core.interceptors.ScopeLevel;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.model.Banner;
import com.ly.imallbatis.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/name/{name}")
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        if (banner == null) {
            throw new NotFoundException(30003);
        }
        return banner;
    }
}
