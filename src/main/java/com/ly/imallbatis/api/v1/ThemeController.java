package com.ly.imallbatis.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.model.Theme;
import com.ly.imallbatis.service.ThemeServices;
import com.ly.imallbatis.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/theme")
@RestController
public class ThemeController {

    @Autowired
    private ThemeServices themeServices;

    @GetMapping("/by/names")
    public List<ThemePureVO> getThemeGroupByName(@RequestParam("names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeServices.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName){
        Theme theme = this.themeServices.findByName(themeName);
        return theme;
    }
}
