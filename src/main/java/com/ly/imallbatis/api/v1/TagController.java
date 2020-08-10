package com.ly.imallbatis.api.v1;

import com.ly.imallbatis.model.Tag;
import com.ly.imallbatis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/type/{type}")
    public List<Tag> getTags(@PathVariable Integer type) {
        return tagService.getByType(type);
    }
}
